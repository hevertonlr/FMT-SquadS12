package com.fmt.app.average.services;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.AlunoRepository;
import com.fmt.app.average.repositories.DisciplinaRepository;
import com.fmt.app.average.repositories.MatriculaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

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

    public List<MatriculaEntity> buscarTodasMatriculasPorAlunoId(Long id) {
        alunoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Aluno não encontrado com id: " + id)
        );
        return matriculaRepository.findAllByAlunoId(id);
    }

    public List<MatriculaEntity> buscarTodasMatriculasPorDisciplinaId(Long id) {
        String entityName = "Matricula";
        disciplinaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Disciplina não encontrada com id: " + id)
        );
        log.info("Buscando todos os " + entityName + " por id de Disciplina");
        List<MatriculaEntity> entities = matriculaRepository.findByDisciplinaId(id);
        log.info("Buscando todos os " + entityName + " por id de Disciplina -> {} Encontrados", entities.size());
        log.debug("Buscando todos os " + entityName + " por id de Disciplina -> Registros encontrados:\n{}\n", objetoParaJson(entities));
        return entities;
    }

}
