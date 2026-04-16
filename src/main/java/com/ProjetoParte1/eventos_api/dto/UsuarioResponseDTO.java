package com.ProjetoParte1.eventos_api.dto;

import com.ProjetoParte1.eventos_api.model.TipoUsuario;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}