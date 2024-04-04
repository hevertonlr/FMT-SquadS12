package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "disciplina")
@EqualsAndHashCode(callSuper = true)
public class DisciplinaEntity extends GenericEntity{
    @Column(length = 150,nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = false)
    private ProfessorEntity professor;
}
