package com.jfc.superheroes.service;

import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.entities.HeroEntity;
import com.jfc.superheroes.exceptions.HeroNotFoundException;
import com.jfc.superheroes.repository.HeroesRepository;
import com.jfc.superheroes.utils.Utils;
import com.jfc.superheroes.utils.Validations.Validations;
import com.jfc.superheroes.utils.log.AswLog;
import com.jfc.superheroes.utils.mappers.AswModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AswLog
@Transactional(rollbackFor = Throwable.class)
public class HeroesServiceImpl implements HeroesService
{
    @Autowired
    private HeroesRepository heroesRepository;

    @Autowired
    private AswModelMapper aswModelMapper;

    private HeroEntity getHeroById(String id )
    {
        HeroEntity heroEntity = heroesRepository.findById( id ).orElse(null);

        if( heroEntity == null )
            throw new HeroNotFoundException( id );

        return heroEntity;
    }

    private boolean isCheckValueForSave( String id, String value, String targetValue )
    {
        return ( Utils.isNullOrEmpty(id)
                || !Utils.isNullOrEmpty( targetValue ) && !targetValue.equalsIgnoreCase( value ) );
    }

    private void checkValuesForSave(HeroDto heroDto, HeroEntity targetHeroEntity)
    {

        Validations.ValidationsBuilder aswValidationsBuilder = Validations.builder();

        String targetHeroname = (targetHeroEntity != null) ? targetHeroEntity.getHeroName() : null;
        if (isCheckValueForSave(heroDto.getId(), heroDto.getHeroName(), targetHeroname))
        {
            if (existHeroName(heroDto.getHeroName()))
                aswValidationsBuilder.alreadyExists("heroName", heroDto.getHeroName() );
        }

        aswValidationsBuilder.build().validate();
    }

    private boolean existHeroName( String heroName )
    {
        if( Utils.isNullOrEmpty(heroName) )
            return false;
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

        HeroEntity exampleHero = HeroEntity.builder().heroName( heroName ).build();
        return heroesRepository.exists(Example.of( exampleHero, matcher ));
    }

    @Override
    public Page<HeroDto> find ( HeroDto filter, Pageable pageable )
    {
        Page<HeroEntity> heroesEntityPage = heroesRepository.findAllHeroes( filter, pageable );

        List<HeroDto> heroDtoList = aswModelMapper.map( heroesEntityPage.toList(), HeroDto.class );

        return new PageImpl<>( heroDtoList, heroesEntityPage.getPageable(), heroesEntityPage.getTotalElements() );

    }

    @Override
    public HeroDto retrieveHero( String id )
    {
        HeroEntity heroEntity = getHeroById( id );
        return aswModelMapper.map(heroEntity, HeroDto.class );
    }


    @Override
    public HeroDto createHero(HeroDto heroDto)
    {
        HeroEntity heroEntity = aswModelMapper.map( heroDto, HeroEntity.class);

        checkValuesForSave ( heroDto, null );

        heroEntity.setNew( true );
        heroEntity = heroesRepository.saveAndFlush(heroEntity);

        return aswModelMapper.map(heroEntity, HeroDto.class );
    }

    @Override
    public HeroDto updateHero( HeroDto heroDto )
    {
        HeroEntity targetHeroEntity = getHeroById( heroDto.getId() );
        checkValuesForSave(heroDto, targetHeroEntity);

        HeroEntity heroEntity = aswModelMapper.map( heroDto, targetHeroEntity );
        heroEntity = heroesRepository.saveAndFlush( heroEntity );

        return aswModelMapper.map( heroEntity, HeroDto.class);
    }

    @Override
    public void deleteHero ( String id )
    {
        HeroEntity heroEntity = getHeroById( id );
        heroesRepository.delete( heroEntity );
    }

}
