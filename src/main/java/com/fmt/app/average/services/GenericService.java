package com.fmt.app.average.services;

import com.fmt.app.average.handlers.InvalidException;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.interfaces.*;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@Service
public abstract class GenericService<T extends IGenericEntity<T>> implements IGenericService<T> {
    private final String entityName;
    protected final IGenericRepository<T> repository;

    public GenericService(IGenericRepository<T> repository) {
        this.repository = repository;
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityName = ((Class<?>) type.getActualTypeArguments()[0]).getSimpleName().replace("Entity","");
    }

    @Override
    public List<T> findAll() {
        log.info("Buscando todos os " + entityName);
        List<T> entities = repository.findAll();
        log.info("Buscando todos os " + entityName + " -> {} Encontrados", entities.size());
        log.debug("Buscando todos os " + entityName + " -> Registros encontrados:\n{}\n", objetoParaJson(entities));
        return entities;
    }

    @Override
    public T findById(Long id) {
        log.info("Buscando " + entityName + " por id ({})", id);

        T entity = repository.findById(id).orElseThrow(() -> {
           log.error("Buscando " + entityName + " por id ({}) -> NÃO Encontrado", id);
           return new NotFoundException(entityName + " não encontrado com id: " + id);
        });

        log.info("Buscando " + entityName + " por id ({}) -> Encontrado", id);
        log.debug("Buscando " + entityName + " por id ({}) -> Registro encontrado:\n{}\n", id, objetoParaJson(entity));

        return entity;
    }

    @Override
    public T insert(T entity) {
        entity.setId(null);
        return save(entity,"Criando");
    }

    @Override
    public T update(T entity) {
        return save(entity,"Alterando");
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            log.info("Excluindo " + entityName + " com id ({}) -> Excluindo", id);
                            throw new NotFoundException(entityName + " não encontrado com id: " + id);
                        });
        log.info("Excluindo " + entityName + " com id ({}) -> Excluído com sucesso", id);
    }

    private T save(T entity,String action) {
        T finalEntity = entity;
        String initLogMessage = action + " " + entityName;
        if(action.equals("Alterando")) {
            finalEntity = findById(entity.getId());
            finalEntity.update(entity);
            log.info(initLogMessage + " com id ({}) -> Salvar: \n{}\n", finalEntity.getId(), objetoParaJson(finalEntity));
        }else
            log.info(initLogMessage + " -> Salvar: \n{}\n", objetoParaJson(finalEntity));

        finalEntity = repository.save(finalEntity);
        log.info(initLogMessage + " -> Salvo com sucesso");
        log.debug(initLogMessage + " -> Registro Salvo: \n{}\n", objetoParaJson(finalEntity));
        return finalEntity;
    }
}
