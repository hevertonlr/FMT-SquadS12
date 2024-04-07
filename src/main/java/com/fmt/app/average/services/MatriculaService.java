package com.fmt.app.average.services;

import com.fmt.app.average.dto.MatriculaDto;
import com.fmt.app.average.entities.AlunoEntity;
import com.fmt.app.average.entities.DisciplinaEntity;
import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.entities.NotaEntity;
import com.fmt.app.average.handlers.NotFoundException;
import com.fmt.app.average.repositories.AlunoRepository;
import com.fmt.app.average.repositories.DisciplinaRepository;
import com.fmt.app.average.repositories.MatriculaRepository;
import com.fmt.app.average.repositories.NotaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;

@Slf4j
@Service
public class MatriculaService extends GenericService<MatriculaEntity> {
    private final String entityName = "Matrícula";
    protected final MatriculaRepository repository;
    protected final AlunoRepository alunoRepository;
    protected final DisciplinaRepository disciplinaRepository;
    protected final NotaRepository notaRepository;

    public MatriculaService(
            MatriculaRepository repository,
            AlunoRepository alunoRepository,
            DisciplinaRepository disciplinaRepository,
            NotaRepository notaRepository
    ){
        super(repository);
        this.repository = repository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.notaRepository = notaRepository;
    }

    public List<MatriculaEntity> findAllByAlunoId(Long id) {
        alunoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Aluno não encontrado com id: " + id)
        );
        log.info("Buscando todos os " + entityName + " por id de Aluno");
        List<MatriculaEntity> entities = repository.findAllByAlunoId(id);
        log.info("Buscando todos os " + entityName + " por id de Aluno -> {} Encontrados", entities.size());
        log.debug(
                "Buscando todos os " + entityName + " por id de Aluno -> Registros encontrados:\n{}\n",
                objetoParaJson(entities)
        );
        return entities;
    }

    public List<MatriculaEntity> findAllByDisciplinaId(Long id) {
        disciplinaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Disciplina não encontrada com id: " + id)
        );
        log.info("Buscando todos os " + entityName + " por id de Disciplina");
        List<MatriculaEntity> entities = repository.findAllByDisciplinaId(id);
        log.info("Buscando todos os " + entityName + " por id de Disciplina -> {} Encontrados", entities.size());
        log.debug(
                "Buscando todos os " + entityName + " por id de Disciplina -> Registros encontrados:\n{}\n",
                objetoParaJson(entities)
        );
        return entities;
    }


    public MatriculaEntity insert(MatriculaDto entityDto) {
        MatriculaEntity entity = new MatriculaEntity();
        entity.setId(null);

        AlunoEntity aluno = alunoRepository.findById(entityDto.alunoId()).orElseThrow(
                () -> new NotFoundException("Aluno não encontrado com id: " + entityDto.alunoId())
        );

        DisciplinaEntity disciplina = disciplinaRepository.findById(entityDto.disciplinaId()).orElseThrow(
                () -> new NotFoundException("Disciplina não encontrada com id: " + entityDto.disciplinaId())
        );

        entity.setDataMatricula(LocalDate.now());
        entity.setMediaFinal(BigDecimal.valueOf(0.0));

        entity.setAluno(aluno);
        entity.setDisciplina(disciplina);

        return save(entity,"Criando");
    }

    public MatriculaEntity update(MatriculaDto entityDto) {
        MatriculaEntity entity = repository.findById(entityDto.id()).orElseThrow(
                () -> new NotFoundException("Matrícula não encontrada com id: " + entityDto.id())
        );

        AlunoEntity aluno = alunoRepository.findById(entityDto.alunoId()).orElseThrow(
                () -> new NotFoundException("Aluno não encontrado com id: " + entityDto.alunoId())
        );

        DisciplinaEntity disciplina = disciplinaRepository.findById(entityDto.disciplinaId()).orElseThrow(
                () -> new NotFoundException("Disciplina não encontrada com id: " + entityDto.disciplinaId())
        );

        return save(entity,"Alterando");
    }

    public void delete(Long id) {
        MatriculaEntity entity = repository.findById(id).orElseThrow(
                () -> {
                    log.error("Excluindo " + entityName + " com id ({}) -> NÃO Encontrado", id);
                    return new NotFoundException(entityName + " não encontrado com id: " + id);
                }
        );

        List<NotaEntity> notas = notaRepository.findAllByMatriculaId(id);

        if(!notas.isEmpty()){
            // TODO
            throw new RuntimeException("Notas lançadas para esta matrícula.");
        }

        repository.delete(entity);
        log.info("Excluindo " + entityName + " com id ({}) -> Excluído com sucesso", id);
    }
}
