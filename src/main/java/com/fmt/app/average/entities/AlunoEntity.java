package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;


@Entity
@Table(name = "aluno")
@EqualsAndHashCode(callSuper = true)
public class AlunoEntity extends GenericEntity {
    @Column(length = 150, nullable = false)
    private String nome;
    @Temporal(value = TemporalType.DATE)
    private LocalDate nascimento;

}
