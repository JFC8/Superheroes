package com.jfc.superheroes.controller;

import com.jfc.superheroes.AbstractIntegrationTest;
import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.dtos.HeroRequest;
import com.jfc.superheroes.factory.HeroFactory;
import com.jfc.superheroes.utils.mappers.AswObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HeroesControllerTest extends AbstractIntegrationTest
{
    @Autowired
    private AswObjectMapper aswObjectMapper;
    private static final String MODULE = "/heroes";
    private static String createHeroId;

    @Test
    @Order(1)
    public void heroes_find_test() throws Exception
    {
        String url = getUrl(MODULE);

        HeroRequest heroRequest = HeroFactory.getHeroRequest();

        mockMvc
                .perform(get(url)
                        .queryParam("heroName", heroRequest.getHeroName() )
                        .queryParam("firstName", heroRequest.getFirstName() )
                        .queryParam("lastName", heroRequest.getLastName() )
                        .queryParam("power", heroRequest.getPower() )
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
        String url = MODULE + "/" + HeroFactory.HEROID;

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
                        .content(aswObjectMapper.writeValueAsString(heroRequest)))
                .andDo( print() )
                .andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();

        HeroDto returnedHeroDto = aswObjectMapper.readValue(responseJson, HeroDto.class);

        createHeroId = returnedHeroDto.getId();

    }

    @Test
    @Order(4)
    public void update_hero_test() throws Exception
    {
        String url = MODULE + "/" + HeroFactory.HEROID;

        HeroRequest heroRequest =  HeroFactory.getHeroRequest();
        heroRequest.setPower("Arachnid sense");

        mockMvc
                .perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(aswObjectMapper.writeValueAsString(heroRequest)))
                .andDo( print() )
                .andExpect( status().isOk() );

    }

    @Test
    @Order(5)
    public void delete_hero_test() throws Exception
    {
        String url = MODULE + "/" + HeroFactory.HEROID;

        mockMvc
                .perform(delete(url))
                .andDo( print() )
                .andExpect( status().isOk() );

    }



}
