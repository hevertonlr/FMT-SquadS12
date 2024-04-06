package com.fmt.app.average.interfaces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Qualifier("IGenericRepository")
@NoRepositoryBean
@Repository
public interface IGenericRepository<T extends IGenericEntity<T>> extends JpaRepository<T, Long> {
}
