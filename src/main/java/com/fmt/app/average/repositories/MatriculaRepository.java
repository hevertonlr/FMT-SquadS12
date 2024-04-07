package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    List<MatriculaEntity> findByAlunoId(Long idAluno);
    List<MatriculaEntity> findByDisciplinaId(Long idDisciplina);
}
