package com.ProjetoParte1.eventos_api.controller;

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
import com.ProjetoParte1.eventos_api.dto.EventoDTO;

@Validated
@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @Operation(summary = "Listar eventos", description = "Retorna todos os eventos paginados")
    @GetMapping
    public ResponseEntity<Page<Evento>> listar(Pageable pageable){
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(summary = "Buscar evento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Evento>> buscar(@PathVariable @Positive(message = "ID deve ser positivo") Long id) {

        Evento evento = service.buscarPorId(id);

        EntityModel<Evento> resource = EntityModel.of(evento,
                linkTo(methodOn(EventoController.class).buscar(id)).withSelfRel(),
                linkTo(methodOn(EventoController.class).listar(null)).withRel("lista"),
                linkTo(methodOn(EventoController.class).deletar(id)).withRel("delete")
        );

        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Criar evento")
    @ApiResponse(responseCode = "201", description = "Evento criado com sucesso")
    @PostMapping
    public ResponseEntity<Evento> salvar(@Valid @RequestBody EventoDTO dto){
        Evento novo = service.salvar(dto);
        return ResponseEntity.status(201).body(novo);
    }

    @Operation(summary = "Deletar evento")
    @ApiResponse(responseCode = "204", description = "Evento deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable @Positive(message = "ID deve ser positivo") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar evento")
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable @Positive(message = "ID deve ser positivo") Long id, @RequestBody EventoDTO dto) {
        Evento atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Buscar eventos por nome")
    @GetMapping("/buscar")
    public ResponseEntity<Page<Evento>> buscarPorNome(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorNome(nome, pageable));
    }
}