package com.fmt.app.average.interfaces;

import com.fmt.app.average.entities.GenericEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGenericService<T extends GenericEntity> {
    List<T> findAll();
    T findById(long id);
    T insert(T entity);
    T update(T entity);
    void delete(long id);
}
