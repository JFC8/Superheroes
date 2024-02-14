package com.jfc.superheroes.service;

import com.jfc.superheroes.AbstractIntegrationTest;
import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.entities.HeroEntity;
import com.jfc.superheroes.exceptions.HeroNotFoundException;
import com.jfc.superheroes.repository.HeroesRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
public class HeroesServiceTest extends AbstractIntegrationTest
{

    @MockBean
    private HeroesRepository heroesRepository;

    @Autowired
    private HeroesServiceImpl heroesService;

        @Test
        @Order(1)
        public void whenGivenId_shouldReturnHero_ifFound()
        {
            HeroDto heroDto = HeroDto.builder()
                    .id("75523408-c8c7-11ee-8b95-0242ac120002")
                    .heroName("Spiderman")
                    .firstName("Peter")
                    .lastName("Parker")
                    .power("Spider")
                    .build();

            HeroEntity heroEntity = HeroEntity.builder()
                    .id("75523408-c8c7-11ee-8b95-0242ac120002")
                    .heroName("Spiderman")
                    .firstName("Peter")
                    .lastName("Parker")
                    .power("Spider")
                    .build();

            given(heroesRepository.findById( heroDto.getId() )).willReturn( Optional.of( heroEntity ) );

            HeroDto savedHeroDto = heroesService.retrieveHero( heroDto.getId() );

            assertThat( savedHeroDto.getHeroName() ).isEqualTo( heroDto.getHeroName() );
        }

        @Test
        @Order(2)
        public void givenExistingHeroName_whenSaveHero_thenTrhowsException()
        {
            HeroDto heroDto = HeroDto.builder()
                    .id("75523408-c8c7-11ee-8b95-0242ac120003")
                    .heroName("Spiderman")
                    .firstName("Peter")
                    .lastName("Parker")
                    .power("Spider")
                    .build();

            when( heroesRepository.findByHeroName(heroDto.getHeroName())).thenReturn(null);

            try{
                heroesService.createHero(heroDto);
            }catch ( HeroNotFoundException heroNotFoundException ){
                assertThat( heroNotFoundException.getErrorCode() ).isEqualTo("hero_not_found");
            }catch (Exception e){
                assertThat(1).isEqualTo(0);
                System.out.println("Not the correct exception");
            }


        }



}
