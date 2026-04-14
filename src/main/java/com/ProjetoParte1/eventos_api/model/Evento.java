package com.ProjetoParte1.eventos_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do evento é obrigatório")
    private String nome;

    private LocalDateTime data;

    @ManyToOne
    private Usuario organizador;

    @ManyToMany
    @JoinTable(
            name = "evento_categoria",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private java.util.List<Categoria> categorias;

    @ManyToOne
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    public java.util.List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(java.util.List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setId(Long id) {
        this.id = id;
    }
}