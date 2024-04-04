package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.interfaces.IGenericService;
import com.fmt.app.average.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aluno")
public class AlunoController extends GenericController<AlunoEntity> {
    public AlunoController(IGenericService<AlunoEntity> service) {
        super(service);
    }
}
