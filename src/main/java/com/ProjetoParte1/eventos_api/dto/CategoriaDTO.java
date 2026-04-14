package com.ProjetoParte1.eventos_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class CategoriaDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Pattern(
            regexp = "^[a-zA-ZÀ-ÿ\\s]+$",
            message = "Nome deve conter apenas letras"
    )
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}