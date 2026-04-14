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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import com.ProjetoParte1.eventos_api.dto.InscricaoDTO;

@Validated
@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    // 🔹 LISTAR
    @Operation(summary = "Listar inscrições")
    @ApiResponse(responseCode = "200", description = "Lista de inscrições retornada com sucesso")
    @GetMapping
    public ResponseEntity<Page<Inscricao>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    // 🔹 BUSCAR POR ID
    @Operation(summary = "Buscar inscrição por ID")
    @ApiResponse(responseCode = "200", description = "Inscrição encontrada")
    @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> buscar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar inscrição")
    @ApiResponse(responseCode = "201", description = "Inscrição criada com sucesso")
    @PostMapping
    public ResponseEntity<Inscricao> salvar(@Valid @RequestBody InscricaoDTO dto) {
        Inscricao nova = service.salvar(dto);
        return ResponseEntity.status(201).body(nova);
    }

    // 🔹 ATUALIZAR (NOVO)
    @Operation(summary = "Atualizar inscrição")
    @ApiResponse(responseCode = "200", description = "Inscrição atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> atualizar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id,
            @Valid @RequestBody InscricaoDTO dto) {

        Inscricao atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // 🔹 DELETAR
    @Operation(summary = "Deletar inscrição")
    @ApiResponse(responseCode = "204", description = "Inscrição deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCA PERSONALIZADA (por usuário)
    @Operation(summary = "Buscar inscrições por usuário")
    @ApiResponse(responseCode = "200", description = "Inscrições encontradas com sucesso")
    @GetMapping("/buscar/usuario")
    public ResponseEntity<Page<Inscricao>> buscarPorUsuario(
            @RequestParam @Positive(message = "ID deve ser positivo") Long usuarioId,
            Pageable pageable) {

        return ResponseEntity.ok(service.buscarPorUsuario(usuarioId, pageable));
    }

    // 🔹 BUSCA PERSONALIZADA (por evento)
    @Operation(summary = "Buscar inscrições por evento")
    @ApiResponse(responseCode = "200", description = "Inscrições encontradas com sucesso")
    @GetMapping("/buscar/evento")
    public ResponseEntity<Page<Inscricao>> buscarPorEvento(
            @RequestParam @Positive(message = "ID deve ser positivo") Long eventoId,
            Pageable pageable) {

        return ResponseEntity.ok(service.buscarPorEvento(eventoId, pageable));
    }
}