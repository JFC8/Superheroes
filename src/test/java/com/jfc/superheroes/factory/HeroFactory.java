package com.jfc.superheroes.factory;

import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.dtos.HeroRequest;
import com.jfc.superheroes.entities.HeroEntity;

import java.util.List;

public class HeroFactory
{
    public static final String HEROID = "75523408-c8c7-11ee-8b95-0242ac120002";
    private static final String HERONAME = "Spiderman";
    private static final String FIRSTNAME = "Peter";
    private static final String LASTNAME = "Parker";
    private static final String POWER = "Spider";

    public static final String HEROID2 = "d40319f6-c8e7-11ee-a234-0242ac140002";
    private static final String HERONAME2 = "Superman";
    private static final String FIRSTNAME2 = "Clark";
    private static final String LASTNAME2 = "Kent";
    private static final String POWER2 = "Super";

    public static HeroRequest getHeroRequest()
    {
        return HeroRequest.builder()
                .heroName( HERONAME )
                .firstName( FIRSTNAME )
                .lastName( LASTNAME )
                .power( POWER )
                .build();
    }

    public static HeroDto getHeroDto()
    {
        return HeroDto.builder()
                .heroName( HERONAME )
                .firstName( FIRSTNAME )
                .lastName( LASTNAME )
                .power( POWER )
                .build();
    }

    public static HeroEntity getHeroEntity()
    {
        return HeroEntity.builder()
                .heroName( HERONAME )
                .firstName( FIRSTNAME )
                .lastName( LASTNAME )
                .power( POWER )
                .build();
    }

    private static List<HeroRequest> getHeroes()
    {
        return List.of( HeroRequest.builder()
                        .heroName( HERONAME )
                        .firstName( FIRSTNAME )
                        .lastName( LASTNAME )
                        .power( POWER )
                        .build(),

                        HeroRequest.builder()
                        .heroName( HERONAME2 )
                        .firstName( FIRSTNAME2 )
                        .lastName( LASTNAME2 )
                        .power( POWER2 )
                        .build()
                        );
    }



}
