package com.jfc.superheroes.repository;


import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.entities.HeroEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomHeroesRepository
{
    Page<HeroEntity> findAllHeroes(HeroDto filter, Pageable pageable );

}
