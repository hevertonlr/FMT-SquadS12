package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.handlers.InvalidOperationException;
import com.fmt.app.average.services.NotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@RestController
@RequestMapping("nota")
public class NotaController extends GenericController<NotaEntity> {
    protected final NotaService service;
    public NotaController(NotaService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/matricula/{id}")
    public ResponseEntity<List<NotaEntity>> buscarPorMatriculaId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("GET {} -> Início", requestedValue);

        List<NotaEntity> notas = service.findByMatriculaId(id);

        log.info("GET {} -> Encontrados {} registros", requestedValue, notas.size());
        log.info("GET {} -> 200 OK", requestedValue);
        log.debug("GET {} -> Response Body:\n{}\n", requestedValue, objetoParaJson(notas));
        return ResponseEntity.ok(notas);
    }


    @Override
    public ResponseEntity<NotaEntity> update(@RequestBody NotaEntity entity) {
        throw new InvalidOperationException("Método não implementado!");
    }
}
