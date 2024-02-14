package com.jfc.superheroes.config;

import com.jfc.superheroes.utils.exceptions.GenericExceptionHandler;
import com.jfc.superheroes.utils.log.AswLogAspectConfig;
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

}