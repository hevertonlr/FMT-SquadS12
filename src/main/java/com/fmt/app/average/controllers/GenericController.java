package com.fmt.app.average.controllers;

import com.fmt.app.average.interfaces.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class GenericController<T extends IGenericEntity<T>> implements IGenericController<T> {

    protected final IGenericService<T> service;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET {} -> Início", requestedValue);
        T entity = service.findById(id);
        log.info("GET {} -> Encontrado", requestedValue);
        log.info("GET {} -> 200 OK", requestedValue);
        log.debug("GET {} -> Response Body:\n{}\n", requestedValue, objetoParaJson(entity));
        return ResponseEntity.ok(entity);
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<T>> list() {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET {} -> Início", requestedValue);
        List<T> entities = service.findAll();
        log.info("GET {} -> Encontrados {} registros", requestedValue, entities.size());
        log.info("GET {} -> 200 OK", requestedValue);
        log.debug("GET {} -> Response Body:\n{}\n", requestedValue, objetoParaJson(entities));
        return ResponseEntity.ok(entities);
    }

    @Override
    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("POST {}", requestedValue);
        entity = service.insert(entity);
        log.info("POST {} -> Cadastrado", requestedValue);
        log.info("POST {} -> 201 CREATED", requestedValue);
        log.debug("POST {} -> Response Body:\n{}\n", requestedValue, objetoParaJson(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(entity); // Retorna 201
    }

    @Override
    @PutMapping()
    public ResponseEntity<T> update(@RequestBody T entity) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("PUT {}", requestedValue);
        entity = service.update(entity);
        log.info("PUT {} -> Atualizado", requestedValue);
        log.info("PUT {} -> 200 OK", requestedValue);
        log.debug("PUT {} -> Response Body:\n{}\n", requestedValue, objetoParaJson(entity));
        return ResponseEntity.ok(entity);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("DELETE {}", requestedValue);
        service.delete(id);
        log.info("DELETE {} -> Excluído", requestedValue);
        log.info("DELETE {} -> 204 NO CONTENT", requestedValue);
        return ResponseEntity.noContent().build(); // Retorna 204
    }
}
