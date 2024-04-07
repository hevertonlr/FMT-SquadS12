package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends IGenericRepository<MatriculaEntity> {
    List<MatriculaEntity> findAllByAlunoId(Long idAluno);
    List<MatriculaEntity> findAllByDisciplinaId(Long idDisciplina);
}
