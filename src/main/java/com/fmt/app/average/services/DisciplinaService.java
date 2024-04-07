package com.fmt.app.average.services;

import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.DisciplinaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@Service
public class DisciplinaService {
    private String entityName = "Disciplina";
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<DisciplinaEntity> listarTodasDisciplinas() {
        log.info("Buscando todos os " + entityName);
        List< DisciplinaEntity> disciplinas= disciplinaRepository.findAll();
        log.info("Buscando todos os " + entityName + " -> {} Encontrados", disciplinas.size());
        log.debug("Buscando todos os " + entityName + " -> Registros encontrados:\n{}\n", objetoParaJson(disciplinas));
        return disciplinas;
    }

    public DisciplinaEntity buscarDisciplinaPorId(Long id) {
        log.info("Buscando " + entityName + " por id ({})", id);
        Optional<DisciplinaEntity> optionalDisciplina = disciplinaRepository.findById(id);
        log.info("Buscando " + entityName + " por id ({}) -> Encontrado", id);
        log.debug("Buscando " + entityName + " por id ({}) -> Registro encontrado:\n{}\n", id, objetoParaJson(optionalDisciplina));
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
        disciplinaRepository.findById(id)
                .ifPresentOrElse(
                        disciplinaRepository::delete,
                        () -> {
                            log.info("Excluindo " + entityName + " com id ({}) -> Excluindo", id);
                            throw new NotFoundException("Professor não encontrado com id: " + id);
                        });
        log.info("Excluindo " + entityName + " com id ({}) -> Excluído com sucesso", id);
    }

}
