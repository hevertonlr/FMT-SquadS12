package com.fmt.app.average.controllers;

import com.fmt.app.average.services.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("matricula")
@RequiredArgsConstructor
public class MatriculaController {
    private final MatriculaService service;
}
