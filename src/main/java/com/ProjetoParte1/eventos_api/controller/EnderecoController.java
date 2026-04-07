package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.model.Endereco;
import com.ProjetoParte1.eventos_api.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    // 🔹 LISTAR
    @Operation(summary = "Listar endereços")
    @GetMapping
    public ResponseEntity<Page<Endereco>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    // 🔹 BUSCAR POR ID
    @Operation(summary = "Buscar endereço por ID")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar endereço")
    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso")
    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
        Endereco novo = service.salvar(endereco);
        return ResponseEntity.status(201).body(novo);
    }

    // 🔹 ATUALIZAR
    @Operation(summary = "Atualizar endereço")
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco atualizado = service.atualizar(id, endereco);
        return ResponseEntity.ok(atualizado);
    }

    // 🔹 DELETAR
    @Operation(summary = "Deletar endereço")
    @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCA PERSONALIZADA
    @Operation(summary = "Buscar endereços por cidade")
    @GetMapping("/buscar")
    public ResponseEntity<Page<Endereco>> buscarPorCidade(@RequestParam String cidade, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorCidade(cidade, pageable));
    }
}