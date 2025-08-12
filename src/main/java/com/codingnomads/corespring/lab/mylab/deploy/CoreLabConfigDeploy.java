package com.codingnomads.corespring.lab.mylab.deploy;

import com.codingnomads.corespring.lab.mylab.Chair;
import com.codingnomads.corespring.lab.mylab.Monitor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;


@Configuration
@Profile("deploy")
@ImportResource({"xml-config/corespring_monitor_configuration.xml"})
public class CoreLabConfigDeploy {


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Chair chair() { return new Chair("Herman Miller", 15); }

    @Bean(name = "deployMonitor")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Monitor deployMonitor() { return new Monitor("ProdM", 12); }


}
