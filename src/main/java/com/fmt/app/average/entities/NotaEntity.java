package com.fmt.app.average.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "notas")
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties("matricula")
public class NotaEntity extends GenericEntity<NotaEntity>{
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

    @Override
    public void update(NotaEntity source) {
        this.nota = source.nota;
        this.coeficiente = source.getCoeficiente();
    }

}
