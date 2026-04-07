package com.ProjetoParte1.eventos_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Inscricao {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataInscricao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Evento evento;
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento(){
        return evento;
    }

    public void setEvento(Evento evento){
        this.evento = evento;
    }
}
