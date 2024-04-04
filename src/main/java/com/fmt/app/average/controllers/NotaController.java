package com.fmt.app.average.controllers;

import com.fmt.app.average.services.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nota")
@RequiredArgsConstructor
public class NotaController {
    private final NotaService service;
}
