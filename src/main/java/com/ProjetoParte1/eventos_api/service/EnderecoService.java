package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.Endereco;
import com.ProjetoParte1.eventos_api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Page<Endereco> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Endereco buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Page<Endereco> buscarPorCidade(String cidade, Pageable pageable) {
        return repository.findByCidadeContainingIgnoreCase(cidade, pageable);
    }

    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        Endereco existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        existente.setRua(endereco.getRua());
        existente.setCidade(endereco.getCidade());

        return repository.save(existente);
    }
}