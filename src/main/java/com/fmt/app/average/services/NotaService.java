package com.fmt.app.average.services;

import com.fmt.app.average.entities.*;
import com.fmt.app.average.handlers.InvalidException;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.MatriculaRepository;
import com.fmt.app.average.repositories.NotaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotaService {
    private final NotaRepository notaRepository;
    private final MatriculaRepository matriculaRepository;
    private final MatriculaService matriculaService;


    //TODO Validações, atribuir professor ja existente, atualizar media
    public NotaEntity cadastrar(NotaEntity entity) {
        if(entity.getNota().doubleValue() < 0 || entity.getNota().doubleValue() > 10){
            throw new InvalidException("Nota invalida, nota atribuida " + entity.getNota().doubleValue());
        }

        if(entity.getCoeficiente().doubleValue() < 0 || entity.getCoeficiente().doubleValue() > 1){
            throw new InvalidException("Coeficiente invalida, Coeficiente atribuida " + entity.getCoeficiente().doubleValue());
        }

        MatriculaEntity matriculaEntity = matriculaRepository.findById(entity.getMatricula().getId()).orElseThrow(
                () -> new NotFoundException("Matricula não encontrada com id: " + entity.getMatricula().getId())
        );

        entity.setMatricula(matriculaEntity);
        entity.setProfessor(matriculaEntity.getDisciplina().getProfessor());
        return notaRepository.save(entity);
    }

    public void remover(Long id) {
        notaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Nota não encontrada com id: " + id)
        );
        notaRepository.deleteById(id);
    }

    public MatriculaEntity buscarMatriculaPorNotaId(Long id) {
        NotaEntity nota = notaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Nota não encontrada com id: " + id)
        );
        return nota.getMatricula();
    }

    public List<NotaEntity> buscarNotasPorMatriculaId(Long matriculaId) {
        matriculaRepository.findById(matriculaId).orElseThrow(
                () -> new NotFoundException("Matrícula ")
        );
        return notaRepository.findAllByMatriculaId(matriculaId);
    }
}
