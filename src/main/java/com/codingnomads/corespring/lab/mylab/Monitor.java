package com.codingnomads.corespring.lab.mylab;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Monitor {

    private String name;
    private Integer sizeInches;


    @PostConstruct
    public void init() {
        System.out.println("Setting up @PostConstruct monitor : " + name +
                " with size: " + sizeInches + " and with hash code: " + this.hashCode());
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Putting back @PreDestroy the monitor : " + name + " with hash code: " + this.hashCode());
    }
}
