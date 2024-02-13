package com.jfc.superheroes.service;

import com.jfc.superheroes.AbstractIntegrationTest;
import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.dtos.HeroRequest;
import com.jfc.superheroes.entities.HeroEntity;
import com.jfc.superheroes.factory.HeroFactory;
import com.jfc.superheroes.repository.HeroesRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class HeroesServiceTest extends AbstractIntegrationTest
{
    @Mock
    private HeroesRepository heroesRepository;

    @InjectMocks
    private HeroesServiceImpl heroesService;

    private HeroRequest heroRequest;
    private HeroDto heroDto;
    private HeroEntity heroEntity;

    @BeforeEach
    public void setup(){
        heroRequest = HeroFactory.getHeroRequest();
        heroDto = HeroFactory.getHeroDto();
        heroEntity = HeroFactory.getHeroEntity();
    }

    @Test
    @Order(1)
    public void whenSaveHeroShouldReturnHero()
    {
        given(heroesRepository.save(heroEntity)).willReturn(heroEntity);

        HeroDto savedHero = heroesService.createHero(heroDto);

        assertThat(savedHero).isNotNull();
    }



}
