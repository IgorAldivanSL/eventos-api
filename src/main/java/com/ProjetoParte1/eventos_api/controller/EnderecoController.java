package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.dto.EnderecoDTO;
import com.ProjetoParte1.eventos_api.dto.EnderecoResponseDTO;
import com.ProjetoParte1.eventos_api.model.Endereco;
import com.ProjetoParte1.eventos_api.service.EnderecoService;

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
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    // 🔹 LISTAR
    @Operation(summary = "Listar todos os endereços")
    @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping
    public ResponseEntity<Page<EnderecoResponseDTO>> listar(Pageable pageable) {

        Page<Endereco> page = service.listar(pageable);
        return ResponseEntity.ok(page.map(service::toDTO));
    }

    // 🔹 BUSCAR POR ID
    @Operation(summary = "Buscar endereço por ID")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "400", description = "Parâmetro inválido")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EnderecoResponseDTO>> buscar(
            @PathVariable @Positive Long id) {

        Endereco endereco = service.buscarPorId(id);
        EnderecoResponseDTO dto = service.toDTO(endereco);

        EntityModel<EnderecoResponseDTO> resource = EntityModel.of(dto,
                linkTo(methodOn(EnderecoController.class).buscar(id)).withSelfRel(),
                linkTo(methodOn(EnderecoController.class).listar(Pageable.unpaged())).withRel("lista"),
                linkTo(methodOn(EnderecoController.class).atualizar(id, null)).withRel("update"),
                linkTo(methodOn(EnderecoController.class).deletar(id)).withRel("delete")
        );

        return ResponseEntity.ok(resource);
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar novo endereço")
    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> salvar(@Valid @RequestBody EnderecoDTO dto) {

        Endereco novo = service.salvar(dto);
        return ResponseEntity.status(201).body(service.toDTO(novo));
    }

    // 🔹 ATUALIZAR
    @Operation(summary = "Atualizar endereço existente")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizar(
            @PathVariable @Positive Long id,
            @Valid @RequestBody EnderecoDTO dto) {

        Endereco atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(service.toDTO(atualizado));
    }

    // 🔹 DELETAR
    @Operation(summary = "Remover endereço")
    @ApiResponse(responseCode = "204", description = "Endereço removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCAR POR CIDADE
    @Operation(summary = "Buscar endereços por cidade")
    @ApiResponse(responseCode = "200", description = "Endereços encontrados")
    @ApiResponse(responseCode = "404", description = "Nenhum endereço encontrado")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping("/buscar")
    public ResponseEntity<Page<EnderecoResponseDTO>> buscarPorCidade(
            @RequestParam String cidade,
            Pageable pageable) {

        Page<Endereco> page = service.buscarPorCidade(cidade, pageable);
        return ResponseEntity.ok(page.map(service::toDTO));
    }
}