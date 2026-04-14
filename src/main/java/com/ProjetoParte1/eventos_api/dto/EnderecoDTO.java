package com.ProjetoParte1.eventos_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EnderecoDTO {

    @NotBlank(message = "Rua é obrigatória")
    @Pattern(
            regexp = ".*[a-zA-ZÀ-ÿ].*",
            message = "Rua deve conter letras"
    )
    private String rua;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
