package com.ProjetoParte1.eventos_api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class InscricaoDTO {

    private LocalDateTime dataInscricao;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "Evento é obrigatório")
    private Long eventoId;

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }
}