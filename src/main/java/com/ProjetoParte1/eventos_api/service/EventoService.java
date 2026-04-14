package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.*;
import com.ProjetoParte1.eventos_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ProjetoParte1.eventos_api.dto.EventoDTO;


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
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public Page<Evento> buscarPorNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Evento salvar(EventoDTO dto){

        Usuario organizador = usuarioRepository.findById(dto.getOrganizadorId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        // 🔥 VALIDAÇÃO CORRETA DAS CATEGORIAS
        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriasIds());

        if (categorias.size() != dto.getCategoriasIds().size()) {
            throw new RuntimeException("Uma ou mais categorias não foram encontradas");
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
                    .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

            Usuario organizador = usuarioRepository.findById(dto.getOrganizadorId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

            List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriasIds());

            existente.setNome(dto.getNome());
            existente.setData(dto.getData());
            existente.setOrganizador(organizador);
            existente.setEndereco(endereco);
            existente.setCategorias(categorias);

            return repository.save(existente);
        }


    public void deletar(Long id) {
        repository.deleteById(id);
    }
}