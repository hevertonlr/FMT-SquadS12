package com.fmt.app.average.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface IGenericEntity<T> {
    void update(T source);
    Long getId();
    void setId(Long id);
}
