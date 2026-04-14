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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import com.ProjetoParte1.eventos_api.dto.EnderecoDTO;

@Validated
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @Operation(summary = "Listar endereços")
    @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso")
    @GetMapping
    public ResponseEntity<Page<Endereco>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(summary = "Buscar endereço por ID")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar endereço")
    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso")
    @PostMapping
    public ResponseEntity<Endereco> salvar(@Valid @RequestBody EnderecoDTO dto) {
        Endereco novo = service.salvar(dto);
        return ResponseEntity.status(201).body(novo);
    }

    @Operation(summary = "Atualizar endereço")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id,
            @Valid @RequestBody EnderecoDTO dto) {

        Endereco atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deletar endereço")
    @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar endereços por cidade")
    @ApiResponse(responseCode = "200", description = "Endereços encontrados com sucesso")
    @GetMapping("/buscar")
    public ResponseEntity<Page<Endereco>> buscarPorCidade(
            @RequestParam String cidade,
            Pageable pageable) {

        return ResponseEntity.ok(service.buscarPorCidade(cidade, pageable));
    }
}