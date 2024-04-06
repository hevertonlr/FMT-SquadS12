package com.fmt.app.average.services;

import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService extends GenericService<ProfessorEntity> {
    public ProfessorService(IGenericRepository<ProfessorEntity> repository){
        super(repository);
    }
}
