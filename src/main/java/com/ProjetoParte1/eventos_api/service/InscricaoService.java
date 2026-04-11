package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.*;
import com.ProjetoParte1.eventos_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ProjetoParte1.eventos_api.dto.InscricaoDTO;



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

    public Inscricao salvar(InscricaoDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Inscricao inscricao = new Inscricao();

        inscricao.setUsuario(usuario);
        inscricao.setEvento(evento);
        inscricao.setDataInscricao(dto.getDataInscricao());

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