package com.jfc.superheroes.service;

import com.jfc.superheroes.dtos.HeroDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HeroesService
{
    Page<HeroDto> find (HeroDto filter, Pageable pageable);
    HeroDto retrieveHero( String id );

}
