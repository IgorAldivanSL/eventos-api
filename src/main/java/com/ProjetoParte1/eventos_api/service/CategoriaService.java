package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.Categoria;
import com.ProjetoParte1.eventos_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Categoria atualizar(Long id, Categoria categoria) {
        Categoria existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        existente.setNome(categoria.getNome());

        return repository.save(existente);
    }
}