package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaEntity,Long> {
}
