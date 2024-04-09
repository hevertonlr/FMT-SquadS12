package com.fmt.app.average.services;

import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.interfaces.*;
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
        log.info("Buscando todos os {}", entityName);
        List<T> entities = repository.findAll();
        log.info("Buscando todos os {} -> {} Encontrados", entityName, entities.size());
        log.debug("Buscando todos os {} -> Registros encontrados:\n{}\n", entityName, objetoParaJson(entities));
        return entities;
    }

    @Override
    public T findById(Long id) {
        log.info("Buscando {} por id ({})", entityName, id);

        T entity = repository.findById(id).orElseThrow(() -> {
            log.error("Buscando {} por id ({}) -> NÃO Encontrado", entityName, id);
           return new NotFoundException(entityName + " não encontrado com id: " + id);
        });

        log.info("Buscando {} por id ({}) -> Encontrado", entityName, id);
        log.debug("Buscando {} por id ({}) -> Registro encontrado:\n{}\n", entityName, id, objetoParaJson(entity));

        return entity;
    }

    @Override
    public T insert(T entity) {
        entity.setId(null);
        return saveGeneric(entity,"Criando");
    }

    @Override
    public T update(T entity) {
        return saveGeneric(entity,"Alterando");
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            log.info("Excluindo {} com id ({}) -> Excluindo", entityName, id);
                            throw new NotFoundException(entityName + " não encontrado com id: " + id);
                        });
        log.info("Excluindo {} com id ({}) -> Excluído com sucesso", entityName, id);
    }

    protected T saveGeneric(T entity, String action) {
        T finalEntity = entity;
        String initLogMessage = action + " " + entityName;
        if(action.equals("Alterando")) {
            finalEntity = findById(entity.getId());
            finalEntity.update(entity);
            log.info("{} com id ({}) -> Salvar: \n{}\n", initLogMessage, finalEntity.getId(), objetoParaJson(finalEntity));
        }else
            log.info("{} -> Salvar: \n{}\n", initLogMessage, objetoParaJson(finalEntity));

        finalEntity = repository.save(finalEntity);
        log.info("{} -> Salvo com sucesso", initLogMessage);
        log.debug("{} -> Registro Salvo: \n{}\n", initLogMessage, objetoParaJson(finalEntity));
        return finalEntity;
    }
}
