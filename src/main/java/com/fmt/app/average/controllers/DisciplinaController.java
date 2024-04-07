package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.services.DisciplinaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;


    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> listarDisciplinas() {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        List<DisciplinaEntity> disciplinas = disciplinaService.listarTodasDisciplinas();
        log.info("GET "+requestedValue+" -> Encontrados {} registros", disciplinas.size());
        log.info("GET "+requestedValue+" -> 200 OK");
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(disciplinas));
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaEntity> buscarDisciplinaPorId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        DisciplinaEntity disciplina = disciplinaService.buscarDisciplinaPorId(id);
        log.info("GET "+requestedValue+" -> Encontrado", id);
        log.info("GET "+requestedValue+" -> 200 OK", id);
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", id, objetoParaJson(disciplina));
        if (disciplina != null) {
            return ResponseEntity.ok(disciplina);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> cadastrarDisciplina(@RequestBody DisciplinaEntity disciplina) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("POST "+requestedValue);
        DisciplinaEntity novaDisciplina = disciplinaService.cadastrar(disciplina);
        log.info("POST "+requestedValue+" -> Cadastrado");
        log.info("POST "+requestedValue+" -> 201 CREATED");
        log.debug("POST "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(novaDisciplina));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaEntity> atualizarDisciplina(@PathVariable Long id, @RequestBody DisciplinaEntity disciplina) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("PUT "+requestedValue, disciplina.getId());
        DisciplinaEntity disciplinaAtualizada = disciplinaService.atualizar(id, disciplina);
        log.info("PUT "+requestedValue+" -> Atualizado", disciplina.getId());
        log.info("PUT "+requestedValue+" -> 200 OK", disciplina.getId());
        log.debug("PUT "+requestedValue+" -> Response Body:\n{}\n", disciplina.getId(), objetoParaJson(disciplina));
        if (disciplinaAtualizada != null) {
            return ResponseEntity.ok(disciplinaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("DELETE "+requestedValue, id);
        disciplinaService.deletar(id);
        log.info("DELETE "+requestedValue+" -> Excluído", id);
        log.info("DELETE "+requestedValue+" -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }


}
