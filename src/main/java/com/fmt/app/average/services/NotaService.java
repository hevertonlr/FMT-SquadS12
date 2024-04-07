package com.fmt.app.average.services;

import com.fmt.app.average.controllers.MatriculaController;
import com.fmt.app.average.entities.*;
import com.fmt.app.average.handlers.InvalidException;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.interfaces.IGenericRepository;
import com.fmt.app.average.repositories.DisciplinaRepository;
import com.fmt.app.average.repositories.MatriculaRepository;
import com.fmt.app.average.repositories.NotaRepository;
import com.fmt.app.average.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    MatriculaService matriculaService;


    //TODO Validações, atribuir professor ja existente, atualizar media
    public NotaEntity cadastrar(NotaEntity entity) {
        if(entity.getNota().doubleValue() < 0 || entity.getNota().doubleValue() > 10){
            new InvalidException("Nota invalida, nota atribuida " + entity.getNota().doubleValue());
        }
        if(entity.getCoeficiente().doubleValue() < 0 || entity.getCoeficiente().doubleValue() > 1){
            new InvalidException("Coeficiente invalida, Coeficiente atribuida " + entity.getCoeficiente().doubleValue());
        }

        MatriculaEntity matriculaEntity = matriculaRepository.findById(entity.getMatricula().getId()).orElseThrow(
                () -> new NotFoundException("Matricula não encontrada com id: " + entity.getMatricula().getId())
        );

        entity.setProfessor(matriculaEntity.getDisciplina().getProfessor());
        return notaRepository.save(entity);
    }

    public void calcularMediaFinalMatricula(MatriculaEntity matricula) {
        BigDecimal somaNotas = BigDecimal.ZERO;
        BigDecimal somaCoeficientes = BigDecimal.ZERO;
        List<NotaEntity> notas = notaRepository.findAllByMatriculaId(matricula.getId());

        for (NotaEntity nota : notas) {
            somaNotas = somaNotas.add(nota.getNota().multiply(nota.getCoeficiente()));
            somaCoeficientes = somaCoeficientes.add(nota.getCoeficiente());
        }

        BigDecimal mediaFinal = somaNotas.divide(somaCoeficientes, RoundingMode.HALF_UP);
        matricula.setMediaFinal(mediaFinal);

        matriculaService.atualizar(matricula.getId(),matricula);
    }

}
