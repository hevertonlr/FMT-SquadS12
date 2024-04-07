package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.services.MatriculaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaEntity>> listarMatriculas() {
        List<MatriculaEntity> matriculas = matriculaService.listarTodasMatriculas();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaEntity> buscarMatriculaPorId(@PathVariable Long id) {
        MatriculaEntity matricula = matriculaService.buscarMatriculaPorId(id);
        if (matricula != null) {
            return ResponseEntity.ok(matricula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<List<MatriculaEntity>> buscarTodasMatricularPorAlunoId(@PathVariable Long id) {
        List<MatriculaEntity> entities = matriculaService.buscarTodasMatriculasPorAlunoId(id);
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/disciplinas/{id}")
    public ResponseEntity<List<MatriculaEntity>> buscarTodasMatriculasPorDisciplinaId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        List<MatriculaEntity> entities = matriculaService.buscarTodasMatriculasPorDisciplinaId(id);
        log.info("GET "+requestedValue+" -> Encontrado", id);
        log.info("GET "+requestedValue+" -> 200 OK", id);
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", id, objetoParaJson(entities));
        return ResponseEntity.ok(entities);
    }

    @PostMapping
    public ResponseEntity<MatriculaEntity> cadastrarMatricula(@RequestBody MatriculaEntity matricula) {
        MatriculaEntity novaMatricula = matriculaService.cadastrar(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMatricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaEntity> atualizarMatricula(@PathVariable Long id, @RequestBody MatriculaEntity matricula) {
        MatriculaEntity matriculaAtualizada = matriculaService.atualizar(id, matricula);
        if (matriculaAtualizada != null) {
            return ResponseEntity.ok(matriculaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Long id) {
        boolean notasLancadas = matriculaService.verificarNotasLancadas(id);
        if (notasLancadas){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            matriculaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
    }

}
