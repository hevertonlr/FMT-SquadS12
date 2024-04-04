package com.fmt.app.average.interfaces;

import com.fmt.app.average.entities.GenericEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("IGenericRepository")
@Repository
public interface IGenericRepository<T extends GenericEntity> extends JpaRepository<T, Long> {
}