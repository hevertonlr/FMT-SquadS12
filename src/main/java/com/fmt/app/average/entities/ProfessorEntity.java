package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = true)
public class ProfessorEntity extends GenericEntity<ProfessorEntity> {

    @Column(length = 150, nullable = false)
    private String nome;

    @Override
    public void update(ProfessorEntity source) {
        this.nome = source.getNome();
    }
}
