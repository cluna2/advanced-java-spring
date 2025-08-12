package com.codingnomads.corespring.lab.mylab.test;

import com.codingnomads.corespring.lab.mylab.Chair;
import com.codingnomads.corespring.lab.mylab.Monitor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@Profile("test")
public class CoreLabConfigTest {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Chair chair() { return new Chair("Herman Miller", 15); }

    @Bean(name = "testMonitor")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Monitor testMonitor() { return new Monitor("TestM", 10); }
}
