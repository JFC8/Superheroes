package com.jfc.superheroes.controller;

import com.jfc.superheroes.AbstractIntegrationTest;
import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.dtos.HeroRequest;
import com.jfc.superheroes.utils.CustomModelMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HeroesControllerTest extends AbstractIntegrationTest
{
    @Autowired
    private CustomModelMapper customModelMapper;

    private static final String MODULE = "/heroes";
    private static final String HEROID = "75523408-c8c7-11ee-8b95-0242ac120002";
    private static final String HERONAME = "Spiderman";
    private static final String FIRSTNAME = "Peter";
    private static final String LASTNAME = "Parker";
    private static final String POWER = "Spider";
    private static String createHeroId;



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

    @Test
    @Order(3)
    public void create_hero_test() throws Exception
    {
        String url = getUrl(MODULE);

        HeroRequest heroRequest = HeroRequest.builder()
                .heroName( "Wonder Woman" )
                .firstName( "Diana" )
                .lastName( "Prince" )
                .power( "Amazonian warrior" )
                .build();

        String responseJson =
                mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(customModelMapper.writeValueAsString(heroRequest)))
                .andDo( print() )
                .andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();

        HeroDto returnedHeroDto = customModelMapper.readValue(responseJson, HeroDto.class);

        createHeroId = returnedHeroDto.getId();

    }

    @Test
    @Order(4)
    public void update_hero_test() throws Exception
    {
        String url = MODULE + "/" + HEROID;

        HeroRequest heroRequest = HeroRequest.builder()
                .heroName( HERONAME )
                .firstName( FIRSTNAME )
                .lastName( LASTNAME )
                .power( "Arachnid sense" )
                .build();

        mockMvc
                .perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(customModelMapper.writeValueAsString(heroRequest)))
                .andDo( print() )
                .andExpect( status().isOk() );

    }



}
