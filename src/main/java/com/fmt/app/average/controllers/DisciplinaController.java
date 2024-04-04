package com.fmt.app.average.controllers;

import com.fmt.app.average.services.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("disciplina")
@RequiredArgsConstructor
public class DisciplinaController {
    private final DisciplinaService service;
}
