package com.fmt.app.average.services;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.repositories.AlunoRepository;
import com.fmt.app.average.repositories.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;

    public BigDecimal calcularMediaGeral(Long id) {
        buscarAlunoPorId(id);
        List<MatriculaEntity> matriculas = matriculaRepository.findAllByAlunoId(id);

        BigDecimal totalMedias = BigDecimal.ZERO;

        for (MatriculaEntity matricula : matriculas) {
            totalMedias = totalMedias.add(matricula.getMediaFinal());
        }

        return totalMedias.divide(
                BigDecimal.valueOf(matriculas.size()),
                RoundingMode.HALF_UP
        );
    }

    public List<AlunoEntity> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public AlunoEntity buscarAlunoPorId(Long id) {
        Optional<AlunoEntity> optionalAluno = alunoRepository.findById(id);
        return optionalAluno.orElse(null);
    }

    public AlunoEntity cadastrar(AlunoEntity aluno) {
        return alunoRepository.save(aluno);
    }

    public AlunoEntity atualizar(Long id, AlunoEntity aluno) {
        if (alunoRepository.existsById(id)) {
            aluno.setId(id);
            return alunoRepository.save(aluno);
        } else {
            return null;
        }
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

}
