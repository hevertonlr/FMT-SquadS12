package com.fmt.app.average.services;

import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService extends GenericService<DisciplinaEntity> {
    public DisciplinaService(IGenericRepository<DisciplinaEntity> repository){
        super(repository);
    }
}
