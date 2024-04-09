package com.fmt.app.average.services;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.handlers.InvalidException;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.MatriculaRepository;
import com.fmt.app.average.repositories.NotaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;
@Slf4j
@Service
public class MatriculaService extends GenericService<MatriculaEntity> {
    private final String entityName = "Matricula";
    protected final MatriculaRepository repository;
    protected final NotaRepository notaRepository;
    public MatriculaService(MatriculaRepository repository,NotaRepository notaRepository){
        super(repository);
        this.repository = repository;
        this.notaRepository = notaRepository;
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
        log.info("Buscando " + entityName + " por {} id ({})", somethingType, somethingId);
        List<MatriculaEntity> entities = (somethingType.equals("Aluno"))
                ? repository.findByAlunoId(somethingId)
                : repository.findByDisciplinaId(somethingId);


        if(entities.isEmpty()){
            log.error("Buscando " + entityName + " por {} id ({}) -> NÃO Encontrado", somethingType, somethingId);
            throw new NotFoundException(entityName + " não encontrado com id de aluno: " + somethingId);
        }

        log.info("Buscando " + entityName + " por {} id ({}) -> Encontrado", somethingType, somethingId);
        log.debug("Buscando " + entityName + " por id ({}) -> Registro encontrado:\n{}\n", somethingId, objetoParaJson(entities));

        return entities;
    }

    public void updateFinalAverage(MatriculaEntity matricula) {
        List<NotaEntity> notas = notaRepository.findAllByMatriculaId(matricula.getId());

        if(notas.isEmpty()) {
            matricula.setMediaFinal(BigDecimal.ZERO);
            update(matricula);
            return;
        }

        BigDecimal somaNotas = BigDecimal.ZERO;
        //BigDecimal somaCoeficientes = BigDecimal.ZERO;

        for (NotaEntity nota : notas) {
            log.debug("somaNota init: {}",somaNotas);
            somaNotas = somaNotas.add(nota.getNota().multiply(nota.getCoeficiente()));
            log.debug("somaNota after: {}",somaNotas);
            //log.debug("somaCoeficientes init: {}",somaCoeficientes);
            //somaCoeficientes = somaCoeficientes.add(nota.getCoeficiente());
            //log.debug("somaCoeficientes after: {}",somaCoeficientes);
        }

        //BigDecimal mediaFinal = somaNotas.divide(somaCoeficientes, RoundingMode.HALF_UP);
        matricula.setMediaFinal(somaNotas);

        update(matricula);
    }

    public BigDecimal getFinalAverage(Long id) {
        List<MatriculaEntity> matriculas = findByAlunoId(id);
        if(matriculas.isEmpty())
            throw new NotFoundException("Aluno não possui Matriculas");

        BigDecimal notas = BigDecimal.ZERO;

        for (MatriculaEntity matricula : matriculas) {
            notas = notas.add(matricula.getMediaFinal().divide(BigDecimal.valueOf(matriculas.size())));
        }

        return notas;
    }
}
