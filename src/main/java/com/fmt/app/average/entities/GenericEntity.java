package com.fmt.app.average.entities;

import com.fmt.app.average.interfaces.IGenericEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@RequiredArgsConstructor
public abstract class GenericEntity<T> implements Serializable, IGenericEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @ColumnDefault(value = "NOW()")
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @ColumnDefault(value = "NOW()")
    protected LocalDateTime modifyAt;

}
