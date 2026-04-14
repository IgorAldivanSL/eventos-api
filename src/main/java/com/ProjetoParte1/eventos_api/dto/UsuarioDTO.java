package com.ProjetoParte1.eventos_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.ProjetoParte1.eventos_api.model.TipoUsuario;

public class UsuarioDTO {

    @NotBlank(message = "NOme e obrigatorio")
    @Pattern(
            regexp = "^[a-zA-ZÀ-ÿ\\s]+$",
            message = "Nome deve conter apenas letras"
    )
    private String nome;

    @Email(message = "Email invalido")
    @NotBlank(message = "Email e obrigatorio")
    private String email;

    @NotNull(message = "Tipo e obrigatorio")
    private TipoUsuario tipo;

    //getters setters

    public String getNome() { return nome ;}
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email;}
    public void setEmail(String email) { this.email =email; }

    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}
