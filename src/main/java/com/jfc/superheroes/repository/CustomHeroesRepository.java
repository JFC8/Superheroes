package com.jfc.superheroes.repository;


import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.entities.HeroesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomHeroesRepository
{
    Page<HeroesEntity> findAllHeroes(HeroDto filter, Pageable pageable );

}
