package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "notas")
public class NotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault(value = "0.00")
    @Column(precision = 5,scale = 2,nullable = false)
    private BigDecimal nota;

    @ColumnDefault(value = "0.00")
    @Column(precision = 19,scale = 6, nullable = false)
    private BigDecimal coeficiente;

    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = false)
    private ProfessorEntity professor;

    @ManyToOne
    @JoinColumn(name = "disciplina_matricula_id",nullable = false)
    private MatriculaEntity matricula;
}
