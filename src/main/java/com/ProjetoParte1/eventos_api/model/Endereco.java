package com.ProjetoParte1.eventos_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Endereco {

    public Endereco() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Rua e obrigatoria")
    private String rua;

    @NotBlank(message = "Cidade e obrigatoria")
    private String cidade;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public String getRua(){
        return rua;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public String getCidade(){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }
}
