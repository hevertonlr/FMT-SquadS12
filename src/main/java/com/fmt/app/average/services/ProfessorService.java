package com.fmt.app.average.services;

import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;

    public List<ProfessorEntity> findAll() {
        List<ProfessorEntity> entities = repository.findAll();
        return entities;
    }

    public ProfessorEntity findById(Long id) {

        ProfessorEntity entity = repository.findById(id).orElseThrow(() -> {
            return new NotFoundException("Professor não encontrado com id: " + id);
        });

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
                            throw new NotFoundException("Professor não encontrado com id: " + id);
                        });

    }

    private ProfessorEntity save(ProfessorEntity entity,String action) {
        ProfessorEntity finalEntity = entity;

        if (action.equals("Alterando")) {
            finalEntity = findById(entity.getId());
            finalEntity.update(entity);
        } else {
            finalEntity = repository.save(finalEntity);

            return finalEntity;
        }
        return finalEntity;
    }
}
