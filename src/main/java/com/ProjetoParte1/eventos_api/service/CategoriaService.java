package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.Categoria;
import com.ProjetoParte1.eventos_api.repository.CategoriaRepository;
import com.ProjetoParte1.eventos_api.dto.CategoriaDTO;
import com.ProjetoParte1.eventos_api.dto.CategoriaResponseDTO;
import com.ProjetoParte1.eventos_api.exception.ResourceNotFoundException;

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
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
    }

    public Page<Categoria> buscarPorNome(String nome, Pageable pageable) {

        Page<Categoria> page = repository.findByNomeContainingIgnoreCase(nome, pageable);

        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma categoria encontrada");
        }

        return page;
    }

    public Categoria salvar(CategoriaDTO dto) {

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());

        return repository.save(categoria);
    }

    public Categoria atualizar(Long id, CategoriaDTO dto) {

        Categoria existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        existente.setNome(dto.getNome());

        return repository.save(existente);
    }

    public void deletar(Long id) {

        Categoria existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        repository.delete(existente);
    }

    // 🔥 MAPPER
    public CategoriaResponseDTO toDTO(Categoria categoria) {

        CategoriaResponseDTO dto = new CategoriaResponseDTO();

        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());

        return dto;
    }
}