package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    List<MatriculaEntity> findAllByAlunoId(Long idAluno);
    List<MatriculaEntity> findAllByDisciplinaId(Long idDisciplina);
}
