package com.fmt.app.average.controllers;

import com.fmt.app.average.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("professor")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService service;
}
