package com.fmt.app.average.interfaces;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGenericService<T extends IGenericEntity<T>> {
    List<T> findAll();
    T findById(Long id);
    T insert(T entity);
    T update(T entity);
    void delete(Long id);
}
