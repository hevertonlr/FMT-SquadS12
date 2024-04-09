package com.fmt.app.average.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = true)
public class ProfessorEntity extends GenericEntity<ProfessorEntity> {

    @Column(length = 150, nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    private List<DisciplinaEntity> disciplinas;

    @Override
    public void update(ProfessorEntity source) {
        this.nome = source.getNome();
    }
}
