package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = true)
public class ProfessorEntity extends GenericEntity{

    @Column(length = 150, nullable = false)
    private String nome;
}
