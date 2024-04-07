package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, Long> {

    List<NotaEntity> findAllByMatriculaId(Long matriculaId);

    List<NotaEntity> findAllById(Long id);
}
