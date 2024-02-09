package com.jfc.superheroes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
public class HeroesServiceImpl implements HeroesService
{

}
