package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.dto.CategoriaDTO;
import com.ProjetoParte1.eventos_api.dto.CategoriaResponseDTO;
import com.ProjetoParte1.eventos_api.model.Categoria;
import com.ProjetoParte1.eventos_api.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    // 🔹 LISTAR
    @Operation(summary = "Listar todas as categorias")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping
    public ResponseEntity<Page<CategoriaResponseDTO>> listar(Pageable pageable) {

        Page<Categoria> page = service.listar(pageable);
        return ResponseEntity.ok(page.map(service::toDTO));
    }

    // 🔹 BUSCAR POR ID
    @Operation(summary = "Buscar categoria por ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @ApiResponse(responseCode = "400", description = "Parâmetro inválido")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CategoriaResponseDTO>> buscar(
            @PathVariable @Positive Long id) {

        Categoria categoria = service.buscarPorId(id);
        CategoriaResponseDTO dto = service.toDTO(categoria);

        EntityModel<CategoriaResponseDTO> resource = EntityModel.of(dto,
                linkTo(methodOn(CategoriaController.class).buscar(id)).withSelfRel(),
                linkTo(methodOn(CategoriaController.class).listar(Pageable.unpaged())).withRel("lista"),
                linkTo(methodOn(CategoriaController.class).atualizar(id, null)).withRel("update"),
                linkTo(methodOn(CategoriaController.class).deletar(id)).withRel("delete")
        );

        return ResponseEntity.ok(resource);
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar nova categoria")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaDTO dto) {

        Categoria nova = service.salvar(dto);
        return ResponseEntity.status(201).body(service.toDTO(nova));
    }

    // 🔹 ATUALIZAR
    @Operation(summary = "Atualizar categoria existente")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(
            @PathVariable @Positive Long id,
            @Valid @RequestBody CategoriaDTO dto) {

        Categoria atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(service.toDTO(atualizada));
    }

    // 🔹 DELETAR
    @Operation(summary = "Remover categoria")
    @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCAR POR NOME
    @Operation(summary = "Buscar categorias por nome")
    @ApiResponse(responseCode = "200", description = "Categorias encontradas")
    @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping("/buscar")
    public ResponseEntity<Page<CategoriaResponseDTO>> buscarPorNome(
            @RequestParam String nome,
            Pageable pageable) {

        Page<Categoria> page = service.buscarPorNome(nome, pageable);
        return ResponseEntity.ok(page.map(service::toDTO));
    }
}