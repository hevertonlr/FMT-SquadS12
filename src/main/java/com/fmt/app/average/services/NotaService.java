package com.fmt.app.average.services;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.handlers.InvalidException;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.NotaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;
@Slf4j
@Service
public class NotaService extends GenericService<NotaEntity> {
    private final String entityName = "Nota";
    protected final NotaRepository repository;
    protected final MatriculaService matriculaService;
    public NotaService(NotaRepository repository,MatriculaService matriculaService){
        super(repository);
        this.repository = repository;
        this.matriculaService = matriculaService;
    }

    public List<NotaEntity> findByMatriculaId(Long matriculaId) {
        log.info("Buscando todos os " + entityName);
        List<NotaEntity> entities = repository.findAllByMatriculaId(matriculaId);
        log.info("Buscando todos os " + entityName + " -> {} Encontrados", entities.size());
        log.debug("Buscando todos os " + entityName + " -> Registros encontrados:\n{}\n", objetoParaJson(entities));
        return entities;
    }

    @Override
    public NotaEntity insert(NotaEntity entity) {
        entity.setId(null);
        if(entity.getNota().doubleValue() < 0 || entity.getNota().doubleValue() > 10){
            throw new InvalidException("Nota invalida, nota atribuída " + entity.getNota().doubleValue());
        }
        if(entity.getCoeficiente().doubleValue() < 0 || entity.getCoeficiente().doubleValue() > 1){
            throw new InvalidException("Coeficiente invalida, Coeficiente atribuída " + entity.getCoeficiente().doubleValue());
        }
        MatriculaEntity matriculaEntity = matriculaService.findById(entity.getMatricula().getId());

        entity.setMatricula(matriculaEntity);
        entity.setProfessor(matriculaEntity.getDisciplina().getProfessor());
        NotaEntity inserted = saveGeneric(entity,"Criando");
        matriculaService.calcularMediaFinal(inserted.getMatricula());
        return inserted;
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(
                        nota -> {
                            MatriculaEntity matricula = nota.getMatricula();
                            repository.delete(nota);
                            matriculaService.calcularMediaFinal(matricula);
                        },
                        () -> {
                            log.info("Excluindo " + entityName + " com id ({}) -> Excluindo", id);
                            throw new NotFoundException(entityName + " não encontrado com id: " + id);
                        });
        log.info("Excluindo " + entityName + " com id ({}) -> Excluído com sucesso", id);
    }
}
