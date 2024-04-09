package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends IGenericRepository<AlunoEntity> {
}
