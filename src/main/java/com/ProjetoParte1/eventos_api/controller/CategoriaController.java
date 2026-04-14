package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.model.Categoria;
import com.ProjetoParte1.eventos_api.service.CategoriaService;

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
import com.ProjetoParte1.eventos_api.dto.CategoriaDTO;

@Validated
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(summary = "Listar categorias")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    @GetMapping
    public ResponseEntity<Page<Categoria>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(summary = "Buscar categoria por ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar categoria")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody CategoriaDTO dto) {
        Categoria nova = service.salvar(dto);
        return ResponseEntity.status(201).body(nova);
    }

    @Operation(summary = "Atualizar categoria")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id,
            @Valid @RequestBody CategoriaDTO dto) {

        Categoria atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @Operation(summary = "Deletar categoria")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar categorias por nome")
    @ApiResponse(responseCode = "200", description = "Categorias encontradas com sucesso")
    @GetMapping("/buscar")
    public ResponseEntity<Page<Categoria>> buscarPorNome(
            @RequestParam String nome,
            Pageable pageable) {

        return ResponseEntity.ok(service.buscarPorNome(nome, pageable));
    }
}