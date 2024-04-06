package com.fmt.app.average.services;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunoService extends GenericService<AlunoEntity> {
    public AlunoService(IGenericRepository<AlunoEntity> repository){
        super(repository);
    }

}
