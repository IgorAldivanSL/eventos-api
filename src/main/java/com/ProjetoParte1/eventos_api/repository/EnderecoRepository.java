package com.ProjetoParte1.eventos_api.repository;

import com.ProjetoParte1.eventos_api.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Page<Endereco> findByCidadeContainingIgnoreCase(String cidade, Pageable pageable);
}
