package com.jfc.superheroes.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroDto
{
    private String id;
    private String heroName;
    private String firstName;
    private String lastName;
    private String power;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
