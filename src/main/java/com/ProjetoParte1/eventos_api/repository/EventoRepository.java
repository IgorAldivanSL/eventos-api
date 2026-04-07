package com.ProjetoParte1.eventos_api.repository;

import com.ProjetoParte1.eventos_api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventoRepository extends JpaRepository<Evento, Long>{
    Page<Evento> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
