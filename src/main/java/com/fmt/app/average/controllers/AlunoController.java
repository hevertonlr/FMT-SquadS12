package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.services.AlunoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> listarAlunos() {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        List<AlunoEntity> alunos = alunoService.listarTodosAlunos();
        log.info("GET "+requestedValue+" -> Encontrados {} registros", alunos.size());
        log.info("GET "+requestedValue+" -> 200 OK");
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(alunos));
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> buscarAlunoPorId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");
        AlunoEntity aluno = alunoService.buscarAlunoPorId(id);
        log.info("GET "+requestedValue+" -> Encontrado", id);
        log.info("GET "+requestedValue+" -> 200 OK", id);
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", id, objetoParaJson(aluno));
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> cadastrarAluno(@RequestBody AlunoEntity aluno) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("POST "+requestedValue);
        AlunoEntity novoAluno = alunoService.cadastrar(aluno);
        log.info("POST "+requestedValue+" -> Cadastrado");
        log.info("POST "+requestedValue+" -> 201 CREATED");
        log.debug("POST "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(novoAluno));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoEntity> atualizarAluno(@PathVariable Long id, @RequestBody AlunoEntity aluno) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("PUT "+requestedValue, aluno.getId());
        AlunoEntity alunoAtualizado = alunoService.atualizar(id, aluno);
        log.info("PUT "+requestedValue+" -> Atualizado", aluno.getId());
        log.info("PUT "+requestedValue+" -> 200 OK", aluno.getId());
        log.debug("PUT "+requestedValue+" -> Response Body:\n{}\n", aluno.getId(), objetoParaJson(aluno));
        if (alunoAtualizado != null) {
            return ResponseEntity.ok(alunoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("DELETE "+requestedValue, id);
        alunoService.deletar(id);
        log.info("DELETE "+requestedValue+" -> Excluído", id);
        log.info("DELETE "+requestedValue+" -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }

}