package com.jfc.superheroes.service;

import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.entities.HeroesEntity;
import com.jfc.superheroes.exceptions.HeroNotFoundException;
import com.jfc.superheroes.repository.HeroesRepository;
import com.jfc.superheroes.utils.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class HeroesServiceImpl implements HeroesService
{
    @Autowired
    private HeroesRepository heroesRepository;

    @Autowired
    private CustomModelMapper customModelMapper;

    private HeroesEntity getHeroById( String id )
    {
        HeroesEntity heroesEntity = heroesRepository.findById( id ).orElse(null);

        if( heroesEntity == null )
            throw new HeroNotFoundException( id );

        return heroesEntity;
    }

    @Override
    public Page<HeroDto> find ( HeroDto filter, Pageable pageable )
    {
        Page<HeroesEntity> heroesEntityPage = heroesRepository.findAllHeroes( filter, pageable );

        List<HeroDto> heroDtoList = customModelMapper.map( heroesEntityPage.toList(), HeroDto.class );

        return new PageImpl<>( heroDtoList, heroesEntityPage.getPageable(), heroesEntityPage.getTotalElements() );

    }

    @Override
    public HeroDto retrieveHero( String id )
    {
        HeroesEntity heroesEntity = getHeroById( id );
        return customModelMapper.map( heroesEntity, HeroDto.class );
    }


}
