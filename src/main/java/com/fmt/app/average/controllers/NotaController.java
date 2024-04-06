package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.interfaces.IGenericService;
import com.fmt.app.average.services.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nota")
public class NotaController extends GenericController<NotaEntity> {

    public NotaController(NotaService service) {
        super(service);
    }
}
