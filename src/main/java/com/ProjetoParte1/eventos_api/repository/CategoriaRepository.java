package com.ProjetoParte1.eventos_api.repository;

import com.ProjetoParte1.eventos_api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    Page<Categoria> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
