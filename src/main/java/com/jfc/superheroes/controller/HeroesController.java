package com.jfc.superheroes.controller;

import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@RestController
@RequestMapping(path = "/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeroesController
{
    @Autowired
    private HeroesService heroesService;

    @GetMapping
    public ResponseEntity<Page<HeroDto>> find (@RequestParam(required = false) String heroName,
                                               @RequestParam(required = false) String firstName,
                                               @RequestParam(required = false) String lastName,
                                               @RequestParam(required = false) String power,
                                               Pageable pageable)
    {
        HeroDto filter = HeroDto.builder()
                .heroName(heroName)
                .firstName(firstName)
                .lastName(lastName)
                .power(power)
                .build();

        return ResponseEntity.ok().body( heroesService.find( filter, pageable) );
    }

}
