package com.jfc.superheroes.repository;

import com.jfc.superheroes.entities.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeroesRepository extends JpaRepository<HeroEntity, String >, CustomHeroesRepository
{
    Optional<HeroEntity> findByHeroName( String heroName );
}
