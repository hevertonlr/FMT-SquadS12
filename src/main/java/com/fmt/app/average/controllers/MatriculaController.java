package com.fmt.app.average.controllers;

import com.fmt.app.average.controllers.dto.FinalAverageResponse;
import com.fmt.app.average.entities.MatriculaEntity;
import com.fmt.app.average.services.MatriculaService;
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

    @GetMapping("aluno/{id}")
    public ResponseEntity<List<MatriculaEntity>> findByAlunoId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        return ResponseEntity.ok(findBySomething(id,requestedValue,"Aluno"));
    }

    @GetMapping("disciplina/{id}")
    public ResponseEntity<List<MatriculaEntity>> findByDisciplinaId(@PathVariable Long id) {
        String requestedValue = (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
        return ResponseEntity.ok(findBySomething(id,requestedValue,"Disciplina"));
    }

    private List<MatriculaEntity> findBySomething(Long id, String requestedValue,String somethingType){
        log.info("GET {} -> Início", requestedValue);
        List<MatriculaEntity> entities = (somethingType.equals("Aluno"))
                ? service.findByAlunoId(id)
                : service.findByDisciplinaId(id);
        log.info("GET {} -> Encontrados {} registros", requestedValue, entities.size());
        log.info("GET {} -> 200 OK", requestedValue);
        log.debug("GET {} -> Response Body:\n{}\n", requestedValue, objetoParaJson(entities));
        return entities;
    }

    @GetMapping("mediafinal/{id}")
    public ResponseEntity<FinalAverageResponse> finalAverage(@PathVariable Long id){
        return ResponseEntity.ok(new FinalAverageResponse(service.getFinalAverage(id)));
    }
}
