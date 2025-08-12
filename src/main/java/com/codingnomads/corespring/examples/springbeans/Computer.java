package com.codingnomads.corespring.examples.springbeans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Computer {

    private String name;
    private String processor;

    public Computer(String name, String processor) {
        this.name = name;
        this.processor = processor;
    }
}
