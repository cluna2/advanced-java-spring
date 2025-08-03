package com.codingnomads.ioc.lab.initial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.codingnomads.ioc.lab.initial")
public class CodingNomadConfiguration {

    @Bean
    public Framework framework() {
        return Framework.builder().name("Spring Boot").version("3.4").build();
    }

    @Bean
    public IDE ide() {
        return IDE.builder().name("Intellij IDEA").version("2025").build();
    }

    @Bean
    public JDK jdk() {
        return JDK.builder().name("OpenJDk").version("17").build();
    }

    @Bean
    public Monitor monitor() {
        return Monitor.builder().name("Medium Monitor").sizeInches(27).build();
    }

    @Bean
    public OfficeChair officeChair() {
        return OfficeChair.builder().name("Herman Miller").reclinable(true).build();
    }
}
