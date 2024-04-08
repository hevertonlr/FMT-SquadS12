package com.fmt.app.average.controllers;

import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.interfaces.IGenericService;
import com.fmt.app.average.services.MatriculaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.fmt.app.average.Utils.Util.objetoParaJson;
@Slf4j
@RestController
@RequestMapping("matricula")
public class MatriculaController extends GenericController<MatriculaEntity> {
    protected final MatriculaService service;
    public MatriculaController(MatriculaService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("aluno/{idAluno}")
    public ResponseEntity<List<MatriculaEntity>> findByAlunoId(@PathVariable Long idAluno) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        return ResponseEntity.ok(findBySomething(idAluno,requestedValue,"Aluno"));
    }

    @GetMapping("disciplina/{idDisciplina}")
    public ResponseEntity<List<MatriculaEntity>> findByDisciplinaId(@PathVariable Long idDisciplina) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        return ResponseEntity.ok(findBySomething(idDisciplina,requestedValue,"Disciplina"));
    }

    private List<MatriculaEntity> findBySomething(Long id, String requestedValue,String somethingType){
        log.info("GET "+requestedValue+" -> Início");
        List<MatriculaEntity> entities = (somethingType.equals("Aluno"))
                ? service.findByAlunoId(id)
                : service.findByDisciplinaId(id);
        log.info("GET "+requestedValue+" -> Encontrados {} registros", entities.size());
        log.info("GET "+requestedValue+" -> 200 OK");
        log.debug("GET "+requestedValue+" -> Response Body:\n{}\n", objetoParaJson(entities));
        return entities;
    }
}
