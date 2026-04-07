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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // 🔹 LISTAR
    @Operation(summary = "Listar usuários")
    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    // 🔹 BUSCAR POR ID (HATEOAS)
    @Operation(summary = "Buscar usuário por ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> buscar(@PathVariable Long id) {

        Usuario usuario = service.buscarPorId(id);

        EntityModel<Usuario> resource = EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).buscar(id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listar(Pageable.unpaged())).withRel("lista"),
                linkTo(methodOn(UsuarioController.class).deletar(id)).withRel("delete"),
                linkTo(methodOn(UsuarioController.class).atualizar(id, usuario)).withRel("update")
        );

        return ResponseEntity.ok(resource);
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario novo = service.salvar(usuario);
        return ResponseEntity.status(201).body(novo);
    }

    // 🔹 ATUALIZAR
    @Operation(summary = "Atualizar usuário")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario atualizado = service.atualizar(id, usuario);
        return ResponseEntity.ok(atualizado);
    }

    // 🔹 DELETAR
    @Operation(summary = "Deletar usuário")
    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCA PERSONALIZADA
    @Operation(summary = "Buscar usuários por nome")
    @GetMapping("/buscar")
    public ResponseEntity<Page<Usuario>> buscarPorNome(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorNome(nome, pageable));
    }
}