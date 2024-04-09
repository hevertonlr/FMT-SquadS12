package com.fmt.app.average.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "aluno")
@EqualsAndHashCode(callSuper = true)
public class AlunoEntity extends GenericEntity<AlunoEntity> {
    @Column(length = 150, nullable = false)
    private String nome;
    @Temporal(value = TemporalType.DATE)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate nascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<MatriculaEntity> matriculas;

    @Override
    public void update(AlunoEntity source) {
        this.nome = source.getNome();
    }

}
