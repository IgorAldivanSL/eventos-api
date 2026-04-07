package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.model.Inscricao;
import com.ProjetoParte1.eventos_api.service.InscricaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    // 🔹 LISTAR
    @Operation(summary = "Listar inscrições")
    @GetMapping
    public ResponseEntity<Page<Inscricao>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    // 🔹 BUSCAR POR ID
    @Operation(summary = "Buscar inscrição por ID")
    @ApiResponse(responseCode = "200", description = "Inscrição encontrada")
    @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar inscrição")
    @ApiResponse(responseCode = "201", description = "Inscrição criada com sucesso")
    @PostMapping
    public ResponseEntity<Inscricao> salvar(@RequestBody Inscricao inscricao) {
        Inscricao nova = service.salvar(inscricao);
        return ResponseEntity.status(201).body(nova);
    }

    // 🔹 DELETAR
    @Operation(summary = "Deletar inscrição")
    @ApiResponse(responseCode = "204", description = "Inscrição deletada com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCA PERSONALIZADA (por usuário)
    @Operation(summary = "Buscar inscrições por usuário")
    @GetMapping("/buscar/usuario")
    public ResponseEntity<Page<Inscricao>> buscarPorUsuario(@RequestParam Long usuarioId, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorUsuario(usuarioId, pageable));
    }

    // 🔹 BUSCA PERSONALIZADA (por evento)
    @Operation(summary = "Buscar inscrições por evento")
    @GetMapping("/buscar/evento")
    public ResponseEntity<Page<Inscricao>> buscarPorEvento(@RequestParam Long eventoId, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorEvento(eventoId, pageable));
    }
}