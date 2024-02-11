package com.jfc.superheroes.repository;

import com.jfc.superheroes.entities.HeroesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroesRepository extends JpaRepository<HeroesEntity, String >, CustomHeroesRepository
{

}
