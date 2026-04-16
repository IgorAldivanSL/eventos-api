package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.dto.UsuarioDTO;
import com.ProjetoParte1.eventos_api.dto.UsuarioResponseDTO;
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

@Validated
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // 🔹 LISTAR
    @Operation(summary = "Listar usuários")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> listar(Pageable pageable) {

        Page<Usuario> page = service.listar(pageable);

        return ResponseEntity.ok(page.map(service::toDTO));
    }

    // 🔹 BUSCAR POR ID (HATEOAS)
    @Operation(summary = "Buscar usuário por ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> buscar(
            @PathVariable @Positive Long id) {

        Usuario usuario = service.buscarPorId(id);
        UsuarioResponseDTO dto = service.toDTO(usuario);

        EntityModel<UsuarioResponseDTO> resource = EntityModel.of(dto,
                linkTo(methodOn(UsuarioController.class).buscar(id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listar(Pageable.unpaged())).withRel("lista"),
                linkTo(methodOn(UsuarioController.class).atualizar(id, null)).withRel("update"),
                linkTo(methodOn(UsuarioController.class).deletar(id)).withRel("delete")
        );

        return ResponseEntity.ok(resource);
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@Valid @RequestBody UsuarioDTO dto) {

        Usuario novo = service.salvar(dto);

        return ResponseEntity.status(201).body(service.toDTO(novo));
    }

    // 🔹 ATUALIZAR
    @Operation(summary = "Atualizar usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable @Positive Long id,
            @Valid @RequestBody UsuarioDTO dto) {

        Usuario atualizado = service.atualizar(id, dto);

        return ResponseEntity.ok(service.toDTO(atualizado));
    }

    // 🔹 DELETAR
    @Operation(summary = "Deletar usuário")
    @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCAR POR NOME
    @Operation(summary = "Buscar usuários por nome")
    @ApiResponse(responseCode = "200", description = "Usuários encontrados")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping("/buscar")
    public ResponseEntity<Page<UsuarioResponseDTO>> buscarPorNome(
            @RequestParam String nome,
            Pageable pageable) {

        Page<Usuario> page = service.buscarPorNome(nome, pageable);

        return ResponseEntity.ok(page.map(service::toDTO));
    }
}