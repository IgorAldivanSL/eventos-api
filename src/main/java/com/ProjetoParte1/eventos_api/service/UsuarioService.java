package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.Usuario;
import com.ProjetoParte1.eventos_api.repository.UsuarioRepository;
import com.ProjetoParte1.eventos_api.dto.UsuarioDTO;
import com.ProjetoParte1.eventos_api.dto.UsuarioResponseDTO;
import com.ProjetoParte1.eventos_api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Page<Usuario> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public Page<Usuario> buscarPorNome(String nome, Pageable pageable) {

        Page<Usuario> page = repository.findByNomeContainingIgnoreCase(nome, pageable);

        if (page.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        return page;
    }

    public Usuario salvar(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTipo(dto.getTipo());

        return repository.save(usuario);
    }

    public Usuario atualizar(Long id, UsuarioDTO dto) {

        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());
        existente.setTipo(dto.getTipo());

        return repository.save(existente);
    }

    public void deletar(Long id) {

        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        repository.delete(existente);
    }

    // 🔥 MAPPER
    public UsuarioResponseDTO toDTO(Usuario usuario) {

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTipo(usuario.getTipo());

        return dto;
    }
}