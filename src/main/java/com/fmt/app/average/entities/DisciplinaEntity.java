package com.fmt.app.average.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "disciplina")
public class DisciplinaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150,nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = false)
    private ProfessorEntity professor;
}
