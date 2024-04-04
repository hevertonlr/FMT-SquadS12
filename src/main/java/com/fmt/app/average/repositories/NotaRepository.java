package com.fmt.app.average.repositories;

import com.fmt.app.average.entities.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity,Long> {
}
