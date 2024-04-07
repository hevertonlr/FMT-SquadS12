package com.fmt.app.average.services;

import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.interfaces.IGenericRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@Service
public class DisciplinaService extends GenericService<DisciplinaEntity> {
    private final String entityName = "Disciplina";
    private final IGenericRepository<ProfessorEntity> professorRepository;

    public DisciplinaService(IGenericRepository<DisciplinaEntity> repository, IGenericRepository<ProfessorEntity> professorRepository) {
        super(repository);
        this.professorRepository = professorRepository;
    }

    @Override
    public DisciplinaEntity insert(DisciplinaEntity entity) {
        entity.setId(null);
        return save(entity, "Criando");
    }

    @Override
    public DisciplinaEntity update(DisciplinaEntity entity) {
        return save(entity, "Alterando");
    }


    DisciplinaEntity save(DisciplinaEntity entity, String action) {
        DisciplinaEntity finalEntity = entity;
        String initLogMessage = action + ' ' + entityName;
        if(action.equals("Alterando")) {
            finalEntity = findById(entity.getId());
            finalEntity.update(entity);
            log.info(initLogMessage + " com id ({}) -> Salvar: \n{}\n", finalEntity.getId(), objetoParaJson(finalEntity));
        }else
            log.info(initLogMessage + " -> Salvar: \n{}\n", objetoParaJson(finalEntity));

        Long idProfessor = entity.getProfessor().getId();
        professorRepository.findById(idProfessor).ifPresentOrElse(
                finalEntity::setProfessor,
                () -> {
                    throw new NotFoundException("Professor não encontrado com id: " + idProfessor);
                });

        finalEntity = repository.save(finalEntity);
        log.info(initLogMessage + " -> Salvo com sucesso");
        log.debug(initLogMessage + " -> Registro Salvo: \n{}\n", objetoParaJson(finalEntity));
        return finalEntity;
    }
}
