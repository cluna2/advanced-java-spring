/* CodingNomads (C)2024 */
package com.codingnomads.ioc.examples.setterinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Laptop {
    private Processor processor;
    private OS os;
    private Disk disk;

    @Autowired
    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    @Autowired
    public void setOs(OS os) {
        this.os = os;
    }

    @Autowired
    public void setDisk(Disk disk) { this.disk = disk; }

    public String printLaptopConfiguration() {
        return "processor: " + processor.getCore() + " core " + processor.getName() + "\nOS: " + os.getName();
    }
}
