package com.jfc.superheroes.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@AutoConfigureBefore({ErrorMvcAutoConfiguration.class})
@Import({BaseConfiguration.class})
@PropertySource({"classpath:application.yml"})
public class BaseAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(BaseAutoConfiguration.class);

    public BaseAutoConfiguration() {
        log.info(this.getClass().getSimpleName() + " loaded");
    }
}