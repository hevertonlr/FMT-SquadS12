package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "disciplina")
@EqualsAndHashCode(callSuper = true)
public class DisciplinaEntity extends GenericEntity<DisciplinaEntity>{
    @Column(length = 150,nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = false)
    private ProfessorEntity professor;

    @Override
    public void update(DisciplinaEntity source) {
        this.nome = source.getNome();
    }

}
