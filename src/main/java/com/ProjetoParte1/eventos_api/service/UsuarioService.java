package com.ProjetoParte1.eventos_api.service;

import com.ProjetoParte1.eventos_api.model.Usuario;
import com.ProjetoParte1.eventos_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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

    public Usuario buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public Page<Usuario> buscarPorNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Usuario salvar (Usuario usuario){
        return repository.save(usuario);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        Usuario existente = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());
        return repository.save(existente);
    }
}
