package com.jfc.superheroes.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfc.superheroes.utils.exceptions.GenericExceptionHandler;
import com.jfc.superheroes.utils.log.AswLogAspectConfig;
import com.jfc.superheroes.utils.mappers.AswModelMapper;
import com.jfc.superheroes.utils.mappers.AswModelMapperImpl;
import com.jfc.superheroes.utils.mappers.AswObjectMapper;
import com.jfc.superheroes.utils.mappers.AswObjectMapperImpl;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(
        proxyBeanMethods = false
)
public class BaseConfiguration {
    public BaseConfiguration() {
    }

    @Bean
    public AswLogAspectConfig aswLogAspectConfig() {
        return new AswLogAspectConfig();
    }

    @Bean
    public GenericExceptionHandler aswGenericExceptionHandler(ErrorAttributes errorAttributes) {
        return new GenericExceptionHandler(errorAttributes);
    }

    @Bean
    public AswObjectMapper aswObjectMapper(ObjectMapper objectMapper) {
        return new AswObjectMapperImpl(objectMapper);
    }

    @Bean
    public AswModelMapper aswModelMapper() {
        return new AswModelMapperImpl();
    }


}