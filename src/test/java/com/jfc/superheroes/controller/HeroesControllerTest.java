package com.jfc.superheroes.controller;

import com.jfc.superheroes.AbstractIntegrationTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HeroesControllerTest extends AbstractIntegrationTest
{
    private static final String MODULE = "/heroes";
    private static final String HEROID = "75523408-c8c7-11ee-8b95-0242ac120002";
    private static final String HERONAME = "Spiderman";
    private static final String FIRSTNAME = "Peter";
    private static final String LASTNAME = "Parker";
    private static final String POWER = "Spider";

    @Test
    @Order(1)
    public void heroes_find_test() throws Exception
    {
        String url = getUrl(MODULE);

        mockMvc
                .perform(get(url)
                        .queryParam("heroName", HERONAME)
                        .queryParam("firstName", FIRSTNAME)
                        .queryParam("lastName", LASTNAME)
                        .queryParam("power", POWER)
                        .queryParam("page", "0")
                        .queryParam("size", "10"))
                .andDo( print() )
                .andExpect( status().isOk() )
        ;
    }

    @Test
    @Order(2)
    public void retrieve_hero_test() throws Exception
    {
        String url = MODULE + "/" + HEROID;

        mockMvc
                .perform( get(url) )
                .andDo( print() )
                .andExpect( status().isOk() )
        ;

    }



}
