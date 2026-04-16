package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.Endereco;
import com.ProjetoParte1.eventos_api.repository.EnderecoRepository;
import com.ProjetoParte1.eventos_api.dto.EnderecoDTO;
import com.ProjetoParte1.eventos_api.dto.EnderecoResponseDTO;
import com.ProjetoParte1.eventos_api.exception.ResourceNotFoundException;

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
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public Page<Endereco> buscarPorCidade(String cidade, Pageable pageable) {

        Page<Endereco> page = repository.findByCidadeContainingIgnoreCase(cidade, pageable);

        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum endereço encontrado");
        }

        return page;
    }

    public Endereco salvar(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setRua(dto.getRua());
        endereco.setCidade(dto.getCidade());
        return repository.save(endereco);
    }

    public Endereco atualizar(Long id, EnderecoDTO dto) {

        Endereco existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));

        existente.setRua(dto.getRua());
        existente.setCidade(dto.getCidade());

        return repository.save(existente);
    }

    public void deletar(Long id) {

        Endereco existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));

        repository.delete(existente);
    }

    // 🔥 MAPPER
    public EnderecoResponseDTO toDTO(Endereco endereco) {

        EnderecoResponseDTO dto = new EnderecoResponseDTO();

        dto.setId(endereco.getId());
        dto.setRua(endereco.getRua());
        dto.setCidade(endereco.getCidade());

        return dto;
    }
}