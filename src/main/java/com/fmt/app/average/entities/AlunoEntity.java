package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "aluno")
@EqualsAndHashCode(callSuper = true)
public class AlunoEntity extends GenericEntity<AlunoEntity> {
    @Column(length = 150, nullable = false)
    private String nome;
    @Temporal(value = TemporalType.DATE)
    private LocalDate nascimento;

    @Override
    public void update(AlunoEntity source) {
        this.nome = source.getNome();
    }

}
