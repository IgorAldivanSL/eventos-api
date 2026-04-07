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

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(summary = "Listar categorias")
    @GetMapping
    public ResponseEntity<Page<Categoria>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(summary = "Buscar categoria por ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar categoria")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
        Categoria nova = service.salvar(categoria);
        return ResponseEntity.status(201).body(nova);
    }

    @Operation(summary = "Atualizar categoria")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria atualizada = service.atualizar(id, categoria);
        return ResponseEntity.ok(atualizada);
    }

    @Operation(summary = "Deletar categoria")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar categorias por nome")
    @GetMapping("/buscar")
    public ResponseEntity<Page<Categoria>> buscarPorNome(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorNome(nome, pageable));
    }
}