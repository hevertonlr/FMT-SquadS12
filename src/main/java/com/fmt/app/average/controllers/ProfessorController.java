package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.interfaces.IGenericService;
import com.fmt.app.average.services.ProfessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("professor")
public class ProfessorController {
    protected final ProfessorService service;

    @GetMapping("{id}")
    public ResponseEntity<ProfessorEntity> findById(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        ProfessorEntity entity = service.findById(id);
        log.info("GET "+requestedValue+" -> Encontrado", id);
        log.info("GET "+requestedValue+" -> 200 OK", id);
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", id, objetoParaJson(entity));
        return ResponseEntity.ok(entity);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> list() {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        List<ProfessorEntity> entities = service.findAll();
        log.info("GET "+requestedValue+" -> Encontrados {} registros", entities.size());
        log.info("GET "+requestedValue+" -> 200 OK");
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(entities));
        return ResponseEntity.ok(entities);
    }

    @PostMapping
    public ResponseEntity<ProfessorEntity> create(@RequestBody ProfessorEntity entity) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("POST "+requestedValue);
        entity = service.insert(entity);
        log.info("POST "+requestedValue+" -> Cadastrado");
        log.info("POST "+requestedValue+" -> 201 CREATED");
        log.debug("POST "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(entity); // Retorna 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorEntity> update(@RequestBody ProfessorEntity entity) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("PUT "+requestedValue, entity.getId());
        entity = service.update(entity);
        log.info("PUT "+requestedValue+" -> Atualizado", entity.getId());
        log.info("PUT "+requestedValue+" -> 200 OK", entity.getId());
        log.debug("PUT "+requestedValue+" -> Response Body:\n{}\n", entity.getId(), objetoParaJson(entity));
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("DELETE "+requestedValue, id);
        service.delete(id);
        log.info("DELETE "+requestedValue+" -> Excluído", id);
        log.info("DELETE "+requestedValue+" -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }
}
