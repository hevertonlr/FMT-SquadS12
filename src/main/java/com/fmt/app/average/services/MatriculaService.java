package com.fmt.app.average.services;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService extends GenericService<MatriculaEntity> {
    public MatriculaService(IGenericRepository<MatriculaEntity> repository){
        super(repository);
    }
}
