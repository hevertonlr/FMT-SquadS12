package com.fmt.app.average.services;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<MatriculaEntity> listarTodasMatriculas() {
        return matriculaRepository.findAll();
    }

    public MatriculaEntity buscarMatriculaPorId(Long id) {
        Optional<MatriculaEntity> optionalMatricula = matriculaRepository.findById(id);
        return optionalMatricula.orElse(null);
    }

    public MatriculaEntity cadastrar(MatriculaEntity matricula) {
        return matriculaRepository.save(matricula);
    }

    public MatriculaEntity atualizar(Long id, MatriculaEntity matricula) {
        if (matriculaRepository.existsById(id)) {
            matricula.setId(id);
            return matriculaRepository.save(matricula);
        } else {
            return null;
        }
    }

    public boolean verificarNotasLancadas(Long id) {
        MatriculaEntity matricula = matriculaRepository.findById(id).orElse(null);
        if (matricula != null) {
            return matricula.getMediaFinal().compareTo(BigDecimal.ZERO) != 0;
        } else {
            throw new NotFoundException("Matricula não encontrada com o ID: " + id);
        }
    }

    public void deletar(Long id) {
        matriculaRepository.deleteById(id);
    }

}
