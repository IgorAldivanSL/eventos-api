package com.ProjetoParte1.eventos_api.repository;

import com.ProjetoParte1.eventos_api.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    Page<Inscricao> findByUsuarioId(Long usuarioId, Pageable pageable);

    Page<Inscricao> findByEventoId(Long eventoId, Pageable pageable);
}