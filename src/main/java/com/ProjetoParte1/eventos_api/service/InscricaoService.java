package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.*;
import com.ProjetoParte1.eventos_api.repository.*;
import com.ProjetoParte1.eventos_api.dto.InscricaoDTO;
import com.ProjetoParte1.eventos_api.dto.InscricaoResponseDTO;
import com.ProjetoParte1.eventos_api.exception.ResourceNotFoundException;

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
                .orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada"));
    }

    public Inscricao salvar(InscricaoDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        Inscricao inscricao = new Inscricao();
        inscricao.setUsuario(usuario);
        inscricao.setEvento(evento);
        inscricao.setDataInscricao(dto.getDataInscricao());

        return repository.save(inscricao);
    }

    public Inscricao atualizar(Long id, InscricaoDTO dto) {

        Inscricao existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        existente.setUsuario(usuario);
        existente.setEvento(evento);
        existente.setDataInscricao(dto.getDataInscricao());

        return repository.save(existente);
    }

    public void deletar(Long id) {

        Inscricao existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada"));

        repository.delete(existente);
    }

    public Page<Inscricao> buscarPorUsuario(Long usuarioId, Pageable pageable) {

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Page<Inscricao> page = repository.findByUsuarioId(usuarioId, pageable);

        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma inscrição encontrada");
        }

        return page;
    }

    public Page<Inscricao> buscarPorEvento(Long eventoId, Pageable pageable) {

        eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        Page<Inscricao> page = repository.findByEventoId(eventoId, pageable);

        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma inscrição encontrada");
        }

        return page;
    }

    // 🔥 MAPPER
    public InscricaoResponseDTO toDTO(Inscricao inscricao) {

        InscricaoResponseDTO dto = new InscricaoResponseDTO();

        dto.setId(inscricao.getId());
        dto.setDataInscricao(inscricao.getDataInscricao());
        dto.setUsuarioNome(inscricao.getUsuario().getNome());
        dto.setEventoNome(inscricao.getEvento().getNome());

        return dto;
    }
}