package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends IGenericRepository<ProfessorEntity> {
}
