package com.jfc.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

//@TestConfiguration(proxyBeanMethods = false)
public class TestSuperheroesApplication {

    public static void main(String[] args) {
        SpringApplication.from(SuperheroesApplication::main).with(TestSuperheroesApplication.class).run(args);
    }

}
