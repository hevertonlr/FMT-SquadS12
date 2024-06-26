package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.interfaces.IGenericService;
import com.fmt.app.average.services.ProfessorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("professores")
public class ProfessorController extends GenericController<ProfessorEntity> {
    public ProfessorController(ProfessorService service) {
        super(service);
    }

}
