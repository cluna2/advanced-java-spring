package com.codingnomads.corespring.examples.profileannotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile("deploy")
@PropertySource(value = "application-deploy.properties")
public class MyPOJO {

    @Value("${deploy.version}")
    private String version;

    public MyPOJO() { System.out.println("This is my POJO"); }

    public String getVersion() {
        return version;
    }
}
