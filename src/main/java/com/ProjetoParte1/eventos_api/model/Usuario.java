package com.ProjetoParte1.eventos_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Usuario {

    public Usuario() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome e obrigartorio")
    private String nome;

    @Email(message = "Email invalido")
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public TipoUsuario getTipo(){
        return tipo;
    }

    public void setTipo(TipoUsuario tipo){
        this.tipo = tipo;
    }
}
