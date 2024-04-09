package com.fmt.app.average.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "disciplina_matricula")
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties("notas")
public class MatriculaEntity extends GenericEntity<MatriculaEntity> {

    @CreationTimestamp
    @Temporal(value = TemporalType.DATE)
    @Column(name="data_matricula", nullable = false)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataMatricula;

    @ColumnDefault(value = "0.00")
    @Column(name="media_final", precision = 5,scale = 2,nullable = false)
    private BigDecimal mediaFinal = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "aluno_id",nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id",nullable = false)
    private DisciplinaEntity disciplina;

    @OneToMany(mappedBy = "matricula", fetch = FetchType.EAGER)
    private List<NotaEntity> notas;

    @Override
    public void update(MatriculaEntity source) {
        this.dataMatricula = source.getDataMatricula();
        this.mediaFinal = source.getMediaFinal();
    }

}
