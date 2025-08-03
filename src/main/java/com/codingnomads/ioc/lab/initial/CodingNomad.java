package com.codingnomads.ioc.lab.initial;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class CodingNomad {
    private final JDK jdk;
    private final IDE ide;
    private final Framework framework;

    @Autowired
    private Monitor monitor;

    private OfficeChair chair;

    @Autowired
    public void setOfficeChair(OfficeChair chair) {
        this.chair = chair;
    }
    public String createAwesomeSoftware() {
        return MessageFormat.format(
                "This coding nomad is creating awesome software using, " +
                        "IDE: ({0}:{1}), JDK: ({2}:{3}), Framework: ({4}:{5}) " +
                        "Monitor: ({6}:{7}), OfficeChair: ({8}:{9})",
                ide.getName(),
                ide.getVersion(),
                jdk.getName(),
                jdk.getVersion(),
                framework.getName(),
                framework.getVersion(),
                monitor.getName(),
                monitor.getSizeInches(),
                chair.getName(),
                chair.isReclinable()
        );
    }
}
