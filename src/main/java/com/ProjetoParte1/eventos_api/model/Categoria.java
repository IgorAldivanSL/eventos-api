package com.ProjetoParte1.eventos_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Categoria {

    public Categoria() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da categoria e obrigatorio")
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Evento> eventos;

    public void setId(Long id){
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}