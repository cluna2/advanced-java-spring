/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.propertysourceannotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class App {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    @Value("${customapp.name}")
    private String customName;

    @Value("${customapp.version}")
    private String customVersion;

    public String getCustomName() { return customName; }

    public String getCustomVersion() { return customVersion; }
}
