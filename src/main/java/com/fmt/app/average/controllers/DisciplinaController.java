package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;


    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> listarDisciplinas() {
        List<DisciplinaEntity> disciplinas = disciplinaService.listarTodasDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaEntity> buscarDisciplinaPorId(@PathVariable Long id) {
        DisciplinaEntity disciplina = disciplinaService.buscarDisciplinaPorId(id);
        if (disciplina != null) {
            return ResponseEntity.ok(disciplina);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> cadastrarDisciplina(@RequestBody DisciplinaEntity disciplina) {
        DisciplinaEntity novaDisciplina = disciplinaService.cadastrar(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaEntity> atualizarDisciplina(@PathVariable Long id, @RequestBody DisciplinaEntity disciplina) {
        DisciplinaEntity disciplinaAtualizada = disciplinaService.atualizar(id, disciplina);
        if (disciplinaAtualizada != null) {
            return ResponseEntity.ok(disciplinaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
            disciplinaService.deletar(id);
            return ResponseEntity.noContent().build();
        }


}
