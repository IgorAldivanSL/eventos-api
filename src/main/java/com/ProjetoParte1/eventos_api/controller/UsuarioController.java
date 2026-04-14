package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.model.Usuario;
import com.ProjetoParte1.eventos_api.service.UsuarioService;

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
import com.ProjetoParte1.eventos_api.dto.UsuarioDTO;

@Validated
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Listar usuários")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> buscar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        Usuario usuario = service.buscarPorId(id);

        EntityModel<Usuario> resource = EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).buscar(id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listar(Pageable.unpaged())).withRel("lista"),
                linkTo(methodOn(UsuarioController.class).deletar(id)).withRel("delete"),
                linkTo(methodOn(UsuarioController.class).atualizar(id, null)).withRel("update")
        );

        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Criar usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @PostMapping
    public ResponseEntity<Usuario> salvar(@Valid @RequestBody UsuarioDTO dto) {
        Usuario novo = service.salvar(dto);
        return ResponseEntity.status(201).body(novo);
    }

    @Operation(summary = "Atualizar usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id,
            @Valid @RequestBody UsuarioDTO dto) {

        Usuario atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deletar usuário")
    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuários por nome")
    @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso")
    @GetMapping("/buscar")
    public ResponseEntity<Page<Usuario>> buscarPorNome(
            @RequestParam String nome,
            Pageable pageable) {

        return ResponseEntity.ok(service.buscarPorNome(nome, pageable));
    }
}