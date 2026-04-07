package com.ProjetoParte1.eventos_api.repository;

import com.ProjetoParte1.eventos_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepository extends  JpaRepository<Usuario, Long>{
    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
