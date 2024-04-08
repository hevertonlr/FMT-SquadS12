package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.services.MatriculaService;
import com.fmt.app.average.services.NotaService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("notas")
@RequiredArgsConstructor
public class NotaController {
    private final NotaService notaService;
    private final MatriculaService matriculaService;

    @GetMapping("/matriculas/{id}")
    public ResponseEntity<List<NotaEntity>> buscarPorMatriculaId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET "+requestedValue+" -> Início");

        List<NotaEntity> notas = notaService.buscarNotasPorMatriculaId(id);

        log.info("GET "+requestedValue+" -> Encontrados {} registros", notas.size());
        log.info("GET "+requestedValue+" -> 200 OK");
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(notas));
        return ResponseEntity.ok(notas);
    }

    @PostMapping
    public ResponseEntity<NotaEntity> cadastrarNota(@RequestBody NotaEntity nota) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("POST "+requestedValue);
        NotaEntity novaNota = notaService.cadastrar(nota);
        log.info("POST "+requestedValue+" -> Cadastrado");
        log.info("POST "+requestedValue+" -> 201 CREATED");
        log.debug("POST "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(novaNota));

        matriculaService.calcularMediaFinal(nota.getMatricula());
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerNota(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("DELETE "+requestedValue, id);
        MatriculaEntity matricula = notaService.buscarMatriculaPorNotaId(id);
        notaService.remover(id);
        log.info("DELETE "+requestedValue+" -> Excluído", id);
        log.info("DELETE "+requestedValue+" -> 204 NO CONTENT", id);

        matriculaService.calcularMediaFinal(matricula);
        return ResponseEntity.noContent().build();
    }
}
