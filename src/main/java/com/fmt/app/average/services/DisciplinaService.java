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
    private final IGenericRepository<ProfessorEntity> professorRepository;

    public DisciplinaService(IGenericRepository<DisciplinaEntity> repository, IGenericRepository<ProfessorEntity> professorRepository) {
        super(repository);
        this.professorRepository = professorRepository;
    }

    @Override
    public DisciplinaEntity insert(DisciplinaEntity entity) {
        entity.setId(null);
        return this.save(entity, "Criando");
    }

    @Override
    public DisciplinaEntity update(DisciplinaEntity entity) {
        return this.save(entity, "Alterando");
    }


    private DisciplinaEntity save(DisciplinaEntity entity, String action) {
        DisciplinaEntity finalEntity = entity;
        String entityName = "Disciplina";
        String initLogMessage = action + ' ' + entityName;
        if(action.equals("Alterando")) {
            finalEntity = findById(entity.getId());
            finalEntity.update(entity);
            log.info("{} com id ({}) -> Salvar: \n{}\n", initLogMessage, finalEntity.getId(), objetoParaJson(finalEntity));
        }else
            log.info("{} -> Salvar: \n{}\n", initLogMessage, objetoParaJson(finalEntity));

        Long idProfessor = entity.getProfessor().getId();
        professorRepository.findById(idProfessor).ifPresentOrElse(
                finalEntity::setProfessor,
                () -> {
                    throw new NotFoundException("Professor não encontrado com id: " + idProfessor);
                });

        finalEntity = repository.save(finalEntity);
        log.info("{} -> Salvo com sucesso", initLogMessage);
        log.debug("{} -> Registro Salvo: \n{}\n", initLogMessage, objetoParaJson(finalEntity));
        return finalEntity;
    }
}
