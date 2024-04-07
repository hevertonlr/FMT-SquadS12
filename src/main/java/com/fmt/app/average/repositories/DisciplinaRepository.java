package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {

}
