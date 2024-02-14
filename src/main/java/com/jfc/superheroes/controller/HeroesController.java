package com.jfc.superheroes.controller;

import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.dtos.HeroRequest;
import com.jfc.superheroes.service.HeroesService;
import com.jfc.superheroes.utils.mappers.AswModelMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeroesController
{
    @Autowired
    private HeroesService heroesService;
    @Autowired
    private AswModelMapper aswModelMapper;

    @GetMapping
    public ResponseEntity<Page<HeroDto>> find ( @RequestParam(required = false) String heroName,
                                                @RequestParam(required = false) String firstName,
                                                @RequestParam(required = false) String lastName,
                                                @RequestParam(required = false) String power,
                                                Pageable pageable )
    {
        HeroDto filter = HeroDto.builder()
                .heroName( heroName )
                .firstName( firstName )
                .lastName( lastName )
                .power( power )
                .build();

        return ResponseEntity.ok().body( heroesService.find( filter, pageable) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroDto> retrieveHero ( @PathVariable String id )
    {
        return ResponseEntity.ok().body( heroesService.retrieveHero( id ) );
    }

    @PostMapping
    public ResponseEntity<HeroDto> createHero ( @RequestBody @Valid HeroRequest heroRequest )
    {
        HeroDto heroDto = aswModelMapper.map( heroRequest, HeroDto.class );
        return ResponseEntity.ok().body( heroesService.createHero( heroDto ) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroDto> updateHero ( @PathVariable String id , @RequestBody @Valid HeroRequest heroRequest )
    {
        HeroDto heroDto = aswModelMapper.map( heroRequest, HeroDto.class );
        heroDto.setId( id );

        return ResponseEntity.ok( heroesService.updateHero ( heroDto ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable String id )
    {
        heroesService.deleteHero( id );
        return ResponseEntity.ok().build();
    }

}
