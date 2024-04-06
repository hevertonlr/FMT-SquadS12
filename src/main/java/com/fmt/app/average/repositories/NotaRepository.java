package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends IGenericRepository<NotaEntity> {
}
