package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "disciplina_matricula")
public class MatriculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault(value = "NOW()")
    @Temporal(value = TemporalType.DATE)
    @Column(name="data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @ColumnDefault(value = "0.00")
    @Column(name="media_final", precision = 5,scale = 2,nullable = false)
    private BigDecimal mediaFinal;

    @ManyToOne
    @JoinColumn(name = "aluno_id",nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id",nullable = false)
    private DisciplinaEntity disciplina;
}
