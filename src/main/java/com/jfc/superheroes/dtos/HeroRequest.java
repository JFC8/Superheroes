package com.jfc.superheroes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroRequest
{

    @NotBlank
    @Size( max = 50 )
    private String heroName;

    @NotBlank
    @Size( max = 50 )
    private String firstName;

    @NotBlank
    @Size( max = 50 )
    private String lastName;

    @NotBlank
    @Size( max = 50 )
    private String power;

}
