package com.codingnomads.ioc.lab.initial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class OfficeChair {
    private String name;
    private boolean reclinable;
}
