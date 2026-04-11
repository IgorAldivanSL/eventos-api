package com.ProjetoParte1.eventos_api.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}