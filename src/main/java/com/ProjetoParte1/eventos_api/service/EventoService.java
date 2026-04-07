package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.*;
import com.ProjetoParte1.eventos_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Evento salvar(Evento evento){

        System.out.println(evento.getOrganizador());
        System.out.println("Evento recebido:");
        System.out.println("Organizador: " + evento.getOrganizador());
        System.out.println("Categorias: " + evento.getCategorias());
        System.out.println("Endereco: " + evento.getEndereco());

        if (evento.getOrganizador() == null || evento.getOrganizador().getId() == null) {
            throw new RuntimeException("Organizador não informado");

        }

        Usuario organizador = usuarioRepository.findById(evento.getOrganizador().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Endereco endereco = enderecoRepository.findById(evento.getEndereco().getId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        List<Categoria> categorias = categoriaRepository.findAllById(
                evento.getCategorias().stream().map(Categoria::getId).toList()
        );

        evento.setOrganizador(organizador);
        evento.setEndereco(endereco);
        evento.setCategorias(categorias);

        return repository.save(evento);
    }

    public Evento atualizar(Long id, Evento evento) {

        Evento existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Usuario organizador = usuarioRepository.findById(evento.getOrganizador().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Endereco endereco = enderecoRepository.findById(evento.getEndereco().getId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        List<Categoria> categorias = categoriaRepository.findAllById(
                evento.getCategorias().stream().map(Categoria::getId).toList()
        );

        existente.setNome(evento.getNome());
        existente.setData(evento.getData());
        existente.setOrganizador(organizador);
        existente.setEndereco(endereco);
        existente.setCategorias(categorias);

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}