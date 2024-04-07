package com.fmt.app.average.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "disciplina")
@RequiredArgsConstructor
public class DisciplinaEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    protected LocalDateTime modifyAt;

    @Column(length = 150,nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id",nullable = false)
    private ProfessorEntity professor;

}
