package com.ProjetoParte1.eventos_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public class EventoDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull(message = "Data é obrigatória")
    private LocalDateTime data;

    @NotNull(message = "Organizador é obrigatório")
    private Long organizadorId;

    @NotNull(message = "Endereço é obrigatório")
    private Long enderecoId;

    @NotNull(message = "Categorias são obrigatórias")
    private List<Long> categoriasIds;

    // getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public Long getOrganizadorId() { return organizadorId; }
    public void setOrganizadorId(Long organizadorId) { this.organizadorId = organizadorId; }

    public Long getEnderecoId() { return enderecoId; }
    public void setEnderecoId(Long enderecoId) { this.enderecoId = enderecoId; }

    public List<Long> getCategoriasIds() { return categoriasIds; }
    public void setCategoriasIds(List<Long> categoriasIds) { this.categoriasIds = categoriasIds; }
}