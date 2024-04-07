package com.fmt.app.average.controllers;

import com.fmt.app.average.dto.MatriculaDto;
import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.services.MatriculaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@RestController
@RequestMapping("matricula")
public class MatriculaController {
    protected final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<MatriculaEntity> findById(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        MatriculaEntity entity = service.findById(id);
        log.info("GET "+requestedValue+" -> Encontrado", id);
        log.info("GET "+requestedValue+" -> 200 OK", id);
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", id, objetoParaJson(entity));
        return ResponseEntity.ok(entity);
    }

    @GetMapping("list")
    public ResponseEntity<List<MatriculaEntity>> list() {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        List<MatriculaEntity> entities = service.findAll();
        log.info("GET "+requestedValue+" -> Encontrados {} registros", entities.size());
        log.info("GET "+requestedValue+" -> 200 OK");
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(entities));
        return ResponseEntity.ok(entities);
    }

    @GetMapping("list/aluno/{id}")
    public ResponseEntity<List<MatriculaEntity>> findAllByAlunoId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        List<MatriculaEntity> entities = service.findAllByAlunoId(id);
        log.info("GET "+requestedValue+" -> Encontrados {} registros", entities.size());
        log.info("GET "+requestedValue+" -> 200 OK");
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(entities));
        return ResponseEntity.ok(entities);
    }

    @GetMapping("list/disciplina/{id}")
    public ResponseEntity<List<MatriculaEntity>> findAllByDisciplinaId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        List<MatriculaEntity> entities = service.findAllByDisciplinaId(id);
        log.info("GET "+requestedValue+" -> Encontrados {} registros", entities.size());
        log.info("GET "+requestedValue+" -> 200 OK");
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(entities));
        return ResponseEntity.ok(entities);
    }

    @PostMapping
    public ResponseEntity<MatriculaEntity> create(@RequestBody MatriculaDto entityDto) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("POST "+requestedValue);
        MatriculaEntity entity = service.insert(entityDto);
        log.info("POST "+requestedValue+" -> Cadastrado");
        log.info("POST "+requestedValue+" -> 201 CREATED");
        log.debug("POST "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(entity); // Retorna 201
    }

    @PutMapping
    public ResponseEntity<MatriculaEntity> update(@RequestBody MatriculaDto entityDto) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("PUT "+requestedValue, entityDto.id());
        MatriculaEntity entity = service.update(entityDto);
        log.info("PUT "+requestedValue+" -> Atualizado", entityDto.id());
        log.info("PUT "+requestedValue+" -> 200 OK", entityDto.id());
        log.debug("PUT "+requestedValue+" -> Response Body:\n{}\n", entityDto.id(), objetoParaJson(entity));
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("DELETE "+requestedValue, id);
        service.delete(id);
        log.info("DELETE "+requestedValue+" -> Excluído", id);
        log.info("DELETE "+requestedValue+" -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }
}
