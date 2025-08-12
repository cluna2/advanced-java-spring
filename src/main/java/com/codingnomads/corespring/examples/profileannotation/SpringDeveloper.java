/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.profileannotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
@PropertySource("application-test.properties")
public class SpringDeveloper {

    @Value("${test.version}")
    private String version;

    public SpringDeveloper() {
        System.out.println("SpringDeveloper is ready.");
    }

    public String getVersion() {
        return version;
    }
}
