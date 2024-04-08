package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.services.DisciplinaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("disciplina")
public class DisciplinaController extends GenericController<DisciplinaEntity> {

    public DisciplinaController(DisciplinaService service) {
        super(service);
    }
}
