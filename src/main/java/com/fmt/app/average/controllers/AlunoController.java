package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> listarAlunos() {
        List<AlunoEntity> alunos = alunoService.listarTodosAlunos();
        return ResponseEntity.ok(alunos);
    }

}