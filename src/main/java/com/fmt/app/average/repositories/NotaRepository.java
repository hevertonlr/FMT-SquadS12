package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends IGenericRepository<NotaEntity> {
    List<NotaEntity> findAllByMatriculaId(Long matriculaId);
}
