package com.fmt.app.average.services;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.handlers.InvalidException;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.interfaces.IGenericRepository;
import com.fmt.app.average.repositories.MatriculaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;
@Slf4j
@Service
public class MatriculaService extends GenericService<MatriculaEntity> {
    private final String entityName = "Matricula";
    protected final MatriculaRepository repository;
    public MatriculaService(MatriculaRepository repository){
        super(repository);
        this.repository = repository;
    }

    public List<MatriculaEntity> findByAlunoId(Long alunoId) {
        return findBySomething(alunoId,"Aluno");
    }
    public List<MatriculaEntity> findByDisciplinaId(Long disciplinaId) {
        return findBySomething(disciplinaId,"Disciplina");
    }

    @Override
    public void delete(Long id) {
        log.info("Excluindo " + entityName + " com id ({}) -> Excluindo", id);
        repository.findById(id)
                .ifPresentOrElse(
                        matricula -> {
                            if(!matricula.getNotas().isEmpty())
                                throw new InvalidException(entityName + " não excluída por possuir notas.");

                            repository.delete(matricula);
                        },
                        () -> {
                            log.error("Buscando " + entityName + " por id ({}) -> NÃO Encontrado", id);
                            throw new NotFoundException(entityName + " não encontrado com id: " + id);
                        });
        log.info("Excluindo " + entityName + " com id ({}) -> Excluído com sucesso", id);
    }

    private List<MatriculaEntity> findBySomething(Long somethingId, String somethingType){
        log.info("Buscando " + entityName + " por "+somethingType+" id ({})", somethingId);
        List<MatriculaEntity> entities = (somethingType.equals("Aluno"))
                ? repository.findByAlunoId(somethingId)
                : repository.findByDisciplinaId(somethingId);


        if(entities.isEmpty()){
            log.error("Buscando " + entityName + " por "+somethingType+" id ({}) -> NÃO Encontrado", somethingId);
            throw new NotFoundException(entityName + " não encontrado com id de aluno: " + somethingId);
        }

        log.info("Buscando " + entityName + " por "+somethingType+" id ({}) -> Encontrado", somethingId);
        log.debug("Buscando " + entityName + " por id ({}) -> Registro encontrado:\n{}\n", somethingId, objetoParaJson(entities));

        return entities;
    }
}
