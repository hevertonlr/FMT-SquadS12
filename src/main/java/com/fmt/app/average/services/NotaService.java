package com.fmt.app.average.services;

import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import com.fmt.app.average.repositories.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class NotaService extends GenericService<NotaEntity> {
    public NotaService(IGenericRepository<NotaEntity> repository){
        super(repository);
    }
}
