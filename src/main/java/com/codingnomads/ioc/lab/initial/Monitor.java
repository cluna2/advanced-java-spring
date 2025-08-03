package com.codingnomads.ioc.lab.initial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Monitor {
    private String name;
    private Integer sizeInches;
}
