package com.fmt.app.average.services;

import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.interfaces.IGenericRepository;
import com.fmt.app.average.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

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
