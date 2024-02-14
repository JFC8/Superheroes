package com.jfc.superheroes.factory;

import com.jfc.superheroes.dtos.HeroRequest;

public class HeroFactory
{
    public static final String HEROID = "75523408-c8c7-11ee-8b95-0242ac120002";
    private static final String HERONAME = "Spiderman";
    private static final String FIRSTNAME = "Peter";
    private static final String LASTNAME = "Parker";
    private static final String POWER = "Spider";

    public static HeroRequest getHeroRequest()
    {
        return HeroRequest.builder()
                .heroName( HERONAME )
                .firstName( FIRSTNAME )
                .lastName( LASTNAME )
                .power( POWER )
                .build();
    }


}
