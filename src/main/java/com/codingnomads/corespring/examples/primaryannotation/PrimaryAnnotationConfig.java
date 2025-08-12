package com.codingnomads.corespring.examples.primaryannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class PrimaryAnnotationConfig {

    @Bean
    public DesktopComputer desktopComputer() { return new DesktopComputer();}
}
