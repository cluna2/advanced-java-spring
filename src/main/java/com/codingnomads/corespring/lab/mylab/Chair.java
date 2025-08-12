package com.codingnomads.corespring.lab.mylab;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chair {

    private String name;
    private Integer height;



    @PostConstruct
    public void init() {
        System.out.println("Chair bean @Component @PostConstruct stage only called once with hash code: " + this.hashCode());
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Chair bean @PreDestroy is being put away!");
    }

}
