package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.*;
import com.ProjetoParte1.eventos_api.repository.*;
import com.ProjetoParte1.eventos_api.exception.ResourceNotFoundException;
import com.ProjetoParte1.eventos_api.exception.BadRequestException;
import com.ProjetoParte1.eventos_api.dto.EventoDTO;
import com.ProjetoParte1.eventos_api.dto.EventoResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Page<Evento> listar(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Evento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
    }

    public Page<Evento> buscarPorNome(String nome, Pageable pageable) {

        Page<Evento> page = repository.findByNomeContainingIgnoreCase(nome, pageable);

        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum evento encontrado");
        }

        return page;
    }

    public Evento salvar(EventoDTO dto){

        if (dto.getData().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Evento não pode ser no passado");
        }

        Usuario organizador = usuarioRepository.findById(dto.getOrganizadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));

        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriasIds());

        if (categorias.size() != dto.getCategoriasIds().size()) {
            throw new BadRequestException("Uma ou mais categorias não foram encontradas");
        }

        Evento evento = new Evento();
        evento.setNome(dto.getNome());
        evento.setData(dto.getData());
        evento.setOrganizador(organizador);
        evento.setEndereco(endereco);
        evento.setCategorias(categorias);

        return repository.save(evento);
    }

    public Evento atualizar(Long id, EventoDTO dto) {

        Evento existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        Usuario organizador = usuarioRepository.findById(dto.getOrganizadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));

        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriasIds());

        if (categorias.size() != dto.getCategoriasIds().size()) {
            throw new BadRequestException("Uma ou mais categorias não foram encontradas");
        }

        existente.setNome(dto.getNome());
        existente.setData(dto.getData());
        existente.setOrganizador(organizador);
        existente.setEndereco(endereco);
        existente.setCategorias(categorias);

        return repository.save(existente);
    }

    public void deletar(Long id) {

        Evento existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        repository.delete(existente);
    }

    // 🔥 MAPPER
    public EventoResponseDTO toDTO(Evento evento) {

        EventoResponseDTO dto = new EventoResponseDTO();

        dto.setId(evento.getId());
        dto.setNome(evento.getNome());
        dto.setData(evento.getData());

        dto.setOrganizadorNome(evento.getOrganizador().getNome());
        dto.setCidade(evento.getEndereco().getCidade());

        dto.setCategorias(
                evento.getCategorias()
                        .stream()
                        .map(Categoria::getNome)
                        .toList()
        );

        return dto;
    }
}