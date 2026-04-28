package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.dto.InscricaoDTO;
import com.ProjetoParte1.eventos_api.dto.InscricaoResponseDTO;
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

@Validated
@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    // 🔹 LISTAR
    @Operation(summary = "Listar todas as inscrições")
    @ApiResponse(responseCode = "200", description = "Lista de inscrições retornada com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping
    public ResponseEntity<Page<InscricaoResponseDTO>> listar(Pageable pageable) {
        Page<Inscricao> page = service.listar(pageable);
        return ResponseEntity.ok(page.map(service::toDTO));
    }

    // 🔹 BUSCAR POR ID
    @Operation(summary = "Buscar inscrição por ID")
    @ApiResponse(responseCode = "200", description = "Inscrição encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<InscricaoResponseDTO> buscar(
            @PathVariable @Positive Long id) {

        Inscricao inscricao = service.buscarPorId(id);
        return ResponseEntity.ok(service.toDTO(inscricao));
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar nova inscrição")
    @ApiResponse(responseCode = "201", description = "Inscrição criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<InscricaoResponseDTO> salvar(
            @Valid @RequestBody InscricaoDTO dto) {

        Inscricao nova = service.salvar(dto);
        return ResponseEntity.status(201).body(service.toDTO(nova));
    }

    // 🔹 ATUALIZAR
    @Operation(summary = "Atualizar inscrição existente")
    @ApiResponse(responseCode = "200", description = "Inscrição atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<InscricaoResponseDTO> atualizar(
            @PathVariable @Positive Long id,
            @Valid @RequestBody InscricaoDTO dto) {

        Inscricao atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(service.toDTO(atualizada));
    }

    // 🔹 DELETAR
    @Operation(summary = "Remover inscrição")
    @ApiResponse(responseCode = "204", description = "Inscrição removida com sucesso")
    @ApiResponse(responseCode = "404", description = "Inscrição não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCAR POR USUÁRIO
    @Operation(summary = "Buscar inscrições pelo ID do usuário")
    @ApiResponse(responseCode = "200", description = "Inscrições encontradas com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping("/buscar/usuario")
    public ResponseEntity<Page<InscricaoResponseDTO>> buscarPorUsuario(
            @RequestParam @Positive(message = "ID deve ser positivo.") Long usuarioId,
            Pageable pageable) {

        Page<Inscricao> page = service.buscarPorUsuario(usuarioId, pageable);
        return ResponseEntity.ok(page.map(service::toDTO));
    }

    // 🔹 BUSCAR POR EVENTO
    @Operation(summary = "Buscar inscrições pelo ID do evento")
    @ApiResponse(responseCode = "200", description = "Inscrições encontradas com sucesso")
    @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping("/buscar/evento")
    public ResponseEntity<Page<InscricaoResponseDTO>> buscarPorEvento(
            @RequestParam  @Positive(message = "ID deve ser positivo.") Long eventoId,
            Pageable pageable) {

        Page<Inscricao> page = service.buscarPorEvento(eventoId, pageable);
        return ResponseEntity.ok(page.map(service::toDTO));
    }
}