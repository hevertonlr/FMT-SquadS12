package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.interfaces.IGenericService;
import com.fmt.app.average.services.NotaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@RestController
@RequestMapping("notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @PostMapping
    public ResponseEntity<NotaEntity> cadastrarAluno(@RequestBody NotaEntity nota) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        log.info("POST "+requestedValue);
        NotaEntity novaNota = notaService.cadastrar(nota);
        log.info("POST "+requestedValue+" -> Cadastrado");
        log.info("POST "+requestedValue+" -> 201 CREATED");
        log.debug("POST "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(novaNota));


        notaService.calcularMediaFinalMatricula(nota.getMatricula());
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }



}
