package com.fmt.app.average.services;

import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<DisciplinaEntity> listarTodasDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public DisciplinaEntity buscarDisciplinaPorId(Long id) {
        Optional<DisciplinaEntity> optionalDisciplina = disciplinaRepository.findById(id);
        return optionalDisciplina.orElse(null);
    }

    public DisciplinaEntity cadastrar(DisciplinaEntity disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public DisciplinaEntity atualizar(Long id, DisciplinaEntity disciplina) {
        if (disciplinaRepository.existsById(id)) {
            disciplina.setId(id);
            return disciplinaRepository.save(disciplina);
        } else {
            return null;
        }
    }

    public void deletar(Long id) {
        disciplinaRepository.deleteById(id);
    }

}
