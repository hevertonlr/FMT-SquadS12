package com.fmt.app.average.services;

import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@Service
public class ProfessorService {
    private String entityName;
    private ProfessorRepository repository;
    void GenericService(ProfessorRepository repository) {
        this.repository = repository;
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityName = ((Class<?>) type.getActualTypeArguments()[0]).getSimpleName().replace("Entity","");
    }

    public List<ProfessorEntity> findAll() {
        log.info("Buscando todos os " + entityName);
        List<ProfessorEntity> entities = repository.findAll();
        log.info("Buscando todos os " + entityName + " -> {} Encontrados", entities.size());
        log.debug("Buscando todos os " + entityName + " -> Registros encontrados:\n{}\n", objetoParaJson(entities));
        return entities;
    }

    public ProfessorEntity findById(Long id) {
        log.info("Buscando " + entityName + " por id ({})", id);

        ProfessorEntity entity = repository.findById(id).orElseThrow(() -> {
            log.error("Buscando " + entityName + " por id ({}) -> NÃO Encontrado", id);
            return new NotFoundException(entityName + " não encontrado com id: " + id);
        });

        log.info("Buscando " + entityName + " por id ({}) -> Encontrado", id);
        log.debug("Buscando " + entityName + " por id ({}) -> Registro encontrado:\n{}\n", id, objetoParaJson(entity));

        return entity;
    }

    public ProfessorEntity insert(ProfessorEntity entity) {
        entity.setId(null);
        return save(entity,"Criando");
    }

    public ProfessorEntity update(ProfessorEntity entity) {
        return save(entity,"Alterando");
    }

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

    private ProfessorEntity save(ProfessorEntity entity,String action) {
        ProfessorEntity finalEntity = entity;
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
