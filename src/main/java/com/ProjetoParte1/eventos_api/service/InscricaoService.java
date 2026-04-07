package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.*;
import com.ProjetoParte1.eventos_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Page<Inscricao> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Inscricao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
    }

    public Inscricao salvar(Inscricao inscricao) {

        if (inscricao.getUsuario() == null || inscricao.getUsuario().getId() == null) {
            throw new RuntimeException("Usuário não informado");
        }

        if (inscricao.getEvento() == null || inscricao.getEvento().getId() == null) {
            throw new RuntimeException("Evento não informado");
        }

        Usuario usuario = usuarioRepository.findById(inscricao.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Evento evento = eventoRepository.findById(inscricao.getEvento().getId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        inscricao.setUsuario(usuario);
        inscricao.setEvento(evento);

        return repository.save(inscricao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Page<Inscricao> buscarPorUsuario(Long usuarioId, Pageable pageable) {
        return repository.findByUsuarioId(usuarioId, pageable);
    }

    public Page<Inscricao> buscarPorEvento(Long eventoId, Pageable pageable) {
        return repository.findByEventoId(eventoId, pageable);
    }
}