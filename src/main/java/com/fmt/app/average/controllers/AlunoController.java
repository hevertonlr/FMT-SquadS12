package com.fmt.app.average.controllers;

import com.fmt.app.average.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aluno")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService service;
}
