package com.fmt.app.average.services;

import com.fmt.app.average.entities.ProfessorEntity;
import com.fmt.app.average.handlers.InvalidException;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.interfaces.IGenericRepository;
import com.fmt.app.average.repositories.MatriculaRepository;
import com.fmt.app.average.repositories.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ProfessorService extends GenericService<ProfessorEntity> {
    private final String entityName = "Professor";
    protected final ProfessorRepository repository;
    public ProfessorService(ProfessorRepository repository){
        super(repository);
        this.repository = repository;
    }

    @Override
    public void delete(Long id) {
        log.info("Excluindo " + entityName + " com id ({}) -> Excluindo", id);
        repository.findById(id)
                .ifPresentOrElse(
                        professor -> {
                            if(!professor.getDisciplinas().isEmpty())
                                throw new InvalidException(entityName + " não excluída por possuir disciplinas.");

                            repository.delete(professor);
                        },
                        () -> {
                            log.error("Buscando " + entityName + " por id ({}) -> NÃO Encontrado", id);
                            throw new NotFoundException(entityName + " não encontrado com id: " + id);
                        });
        log.info("Excluindo " + entityName + " com id ({}) -> Excluído com sucesso", id);
    }
}
