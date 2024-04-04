package com.fmt.app.average.interfaces;

import com.fmt.app.average.entities.GenericEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGenericController<T extends GenericEntity> {

    ResponseEntity<T> findById(Long id);

    ResponseEntity<List<T>> list();

    ResponseEntity<T> create(T entity);

    ResponseEntity<T> update(T entity);

    ResponseEntity<Void> delete(Long id);
}
