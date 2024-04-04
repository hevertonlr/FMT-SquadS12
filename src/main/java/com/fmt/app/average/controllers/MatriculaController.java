package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.interfaces.IGenericService;
import com.fmt.app.average.services.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("matricula")
public class MatriculaController extends GenericController<MatriculaEntity> {
    public MatriculaController(IGenericService<MatriculaEntity> service) {
        super(service);
    }
}
