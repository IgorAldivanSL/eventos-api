package com.ProjetoParte1.eventos_api.dto;

import java.time.LocalDateTime;

public class InscricaoResponseDTO {

    private Long id;
    private LocalDateTime dataInscricao;

    private String usuarioNome;
    private String eventoNome;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDateTime dataInscricao) { this.dataInscricao = dataInscricao; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    public String getEventoNome() { return eventoNome; }
    public void setEventoNome(String eventoNome) { this.eventoNome = eventoNome; }
}