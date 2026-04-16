package com.ProjetoParte1.eventos_api.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EventoResponseDTO {

    private Long id;
    private String nome;
    private LocalDateTime data;

    private String organizadorNome;
    private String cidade;
    private List<String> categorias;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getOrganizadorNome() { return organizadorNome; }
    public void setOrganizadorNome(String organizadorNome) { this.organizadorNome = organizadorNome; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public List<String> getCategorias() { return categorias; }
    public void setCategorias(List<String> categorias) { this.categorias = categorias; }
}