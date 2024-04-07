package com.fmt.app.average.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "aluno")
@Data
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=150, nullable=false)
    private String nome;

    @Temporal(value=TemporalType.DATE)
    private LocalDate dataNascimento;
}
