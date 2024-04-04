package com.fmt.app.average.services;

import com.fmt.app.average.entities.GenericEntity;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.interfaces.IGenericRepository;
import com.fmt.app.average.interfaces.IGenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@Service
public abstract class GenericService<T extends GenericEntity> implements IGenericService<T> {
    private final String entityName;
    protected final IGenericRepository<T> repository;

    public GenericService(IGenericRepository<T> repository) {
        this.repository = repository;
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityName = ((Class<?>) type.getActualTypeArguments()[0]).getSimpleName();
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
    public T findById(long id) {
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

        log.info("Criando " + entityName + " -> Salvar: \n{}\n", objetoParaJson(entity));
        entity = repository.save(entity);

        log.info("Criando " + entityName + " -> Salvo com sucesso");
        log.debug("Criando " + entityName + " -> Registro Salvo: \n{}\n", objetoParaJson(entity));
        return entity;
    }

    @Override
    public T update(T entity) {
        log.info("Alterando " + entityName + " com id ({}) -> Salvar: \n{}\n", entity.getId(), objetoParaJson(entity));
        entity = repository.save(entity);
        log.info("Alterando " + entityName + " -> Salvo com sucesso");
        log.debug("Alterando " + entityName + " -> Registro Salvo: \n{}\n", objetoParaJson(entity));
        return entity;
    }

    @Override
    public void delete(long id) {
        repository.findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            log.info("Excluindo " + entityName + " com id ({}) -> Excluindo", id);
                            throw new NotFoundException(entityName + " não encontrado com id: " + id);
                        });
        log.info("Excluindo " + entityName + " com id ({}) -> Excluído com sucesso", id);
    }
}
