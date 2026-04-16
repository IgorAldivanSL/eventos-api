package com.ProjetoParte1.eventos_api.controller;

import com.ProjetoParte1.eventos_api.dto.EventoDTO;
import com.ProjetoParte1.eventos_api.dto.EventoResponseDTO;
import com.ProjetoParte1.eventos_api.model.Evento;
import com.ProjetoParte1.eventos_api.service.EventoService;

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
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    // 🔹 LISTAR (SEM HATEOAS)
    @Operation(summary = "Listar eventos")
    @ApiResponse(responseCode = "200", description = "Lista de eventos retornada com sucesso")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping
    public ResponseEntity<Page<EventoResponseDTO>> listar(Pageable pageable){

        Page<Evento> page = service.listar(pageable);

        return ResponseEntity.ok(page.map(service::toDTO));
    }

    // 🔹 BUSCAR POR ID (COM HATEOAS)
    @Operation(summary = "Buscar evento por ID")
    @ApiResponse(responseCode = "200", description = "Evento encontrado")
    @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EventoResponseDTO>> buscar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        Evento evento = service.buscarPorId(id);
        EventoResponseDTO dto = service.toDTO(evento);

        EntityModel<EventoResponseDTO> resource = EntityModel.of(dto,
                linkTo(methodOn(EventoController.class).buscar(id)).withSelfRel(),
                linkTo(methodOn(EventoController.class).listar(Pageable.unpaged())).withRel("lista"),
                linkTo(methodOn(EventoController.class).atualizar(id, null)).withRel("update"),
                linkTo(methodOn(EventoController.class).deletar(id)).withRel("delete")
        );

        return ResponseEntity.ok(resource);
    }

    // 🔹 CRIAR
    @Operation(summary = "Criar evento")
    @ApiResponse(responseCode = "201", description = "Evento criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<EventoResponseDTO> salvar(@Valid @RequestBody EventoDTO dto){

        Evento novo = service.salvar(dto);

        return ResponseEntity.status(201).body(service.toDTO(novo));
    }

    // 🔹 ATUALIZAR
    @Operation(summary = "Atualizar evento")
    @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> atualizar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id,
            @Valid @RequestBody EventoDTO dto) {

        Evento atualizado = service.atualizar(id, dto);

        return ResponseEntity.ok(service.toDTO(atualizado));
    }

    // 🔹 DELETAR
    @Operation(summary = "Deletar evento")
    @ApiResponse(responseCode = "204", description = "Evento deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 BUSCAR POR NOME (COM 404 NO SERVICE)
    @Operation(summary = "Buscar eventos por nome")
    @ApiResponse(responseCode = "200", description = "Eventos encontrados com sucesso")
    @ApiResponse(responseCode = "404", description = "Nenhum evento encontrado")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @GetMapping("/buscar")
    public ResponseEntity<Page<EventoResponseDTO>> buscarPorNome(
            @RequestParam String nome,
            Pageable pageable) {

        Page<Evento> page = service.buscarPorNome(nome, pageable);

        return ResponseEntity.ok(page.map(service::toDTO));
    }
}