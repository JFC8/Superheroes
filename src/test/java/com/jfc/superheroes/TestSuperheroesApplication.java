package com.jfc.superheroes;

import org.springframework.boot.SpringApplication;

//@TestConfiguration(proxyBeanMethods = false)
public class TestSuperheroesApplication {

    public static void main(String[] args) {
        SpringApplication.from(SuperheroesApplication::main).with(TestSuperheroesApplication.class).run(args);
    }

}
