package com.ProjetoParte1.eventos_api.dto;

public class EnderecoResponseDTO {

    private Long id;
    private String rua;
    private String cidade;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
}