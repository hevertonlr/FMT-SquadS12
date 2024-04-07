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
@RequestMapping("professores")
public class ProfessorController {
    protected final ProfessorService service;

    @GetMapping("{id}")
    public ResponseEntity<ProfessorEntity> findById(@PathVariable Long id) {
        log.info("GET Professores -> Início");
        ProfessorEntity entity = service.findById(id);
        log.info("GET Professores -> Encontrado", id);
        log.info("GET Professores -> 200 OK", id);
        log.debug("GET Professores -> Response Body:\n{}\n", id, objetoParaJson(entity));
        return ResponseEntity.ok(entity);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> list() {
        log.info("GET Professores -> Início");
        List<ProfessorEntity> entities = service.findAll();
        log.info("GET Professores -> Encontrados {} registros", entities.size());
        log.info("GET Professores -> 200 OK");
        log.debug("GET Professores -> Response Body:\n{}\n", objetoParaJson(entities));
        return ResponseEntity.ok(entities);
    }

    @PostMapping
    public ResponseEntity<ProfessorEntity> create(@RequestBody ProfessorEntity entity) {
        log.info("POST Professores");
        entity = service.insert(entity);
        log.info("POST Professores -> Cadastrado");
        log.info("POST Professores -> 201 CREATED");
        log.debug("POST Professores -> Response Body:\n{}\n", objetoParaJson(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(entity); // Retorna 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorEntity> update(@RequestBody ProfessorEntity entity) {
        log.info("PUT Professores", entity.getId());
        entity = service.update(entity);
        log.info("PUT Professores -> Atualizado", entity.getId());
        log.info("PUT Professores -> 200 OK", entity.getId());
        log.debug("PUT Professores -> Response Body:\n{}\n", entity.getId(), objetoParaJson(entity));
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE Professores", id);
        service.delete(id);
        log.info("DELETE Professores -> Excluído", id);
        log.info("DELETE Professores -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }
}
