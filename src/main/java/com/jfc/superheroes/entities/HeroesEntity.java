package com.jfc.superheroes.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="HeroesEntity")
@Table(name = "heroes")
@EntityListeners(AuditingEntityListener.class)
public class HeroesEntity implements Persistable<String>
{
    @Id
    @UuidGenerator
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "hero_name", nullable = false, unique = true, length = 50)
    private String heroName;

    @Column(name = "first_name", nullable = true, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = true, length = 50)
    private String lastName;

    @Column(name = "power", nullable = true, length = 50)
    private String power;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public boolean isNew() { return getId() == null; }

}
