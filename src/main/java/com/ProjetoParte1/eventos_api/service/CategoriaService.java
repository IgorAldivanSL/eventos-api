package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.Categoria;
import com.ProjetoParte1.eventos_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ProjetoParte1.eventos_api.dto.CategoriaDTO;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Page<Categoria> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Page<Categoria> buscarPorNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Categoria salvar(CategoriaDTO dto) {

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        return repository.save(categoria);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Categoria atualizar(Long id, CategoriaDTO dto) {
        Categoria existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        existente.setNome(dto.getNome());

        return repository.save(existente);
    }
}