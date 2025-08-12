package com.codingnomads.corespring.examples.importannotation;

import com.codingnomads.corespring.examples.profileannotation.SpringDeveloper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    @Bean
    public SpringDeveloper myDev() { return new SpringDeveloper(); }
}
