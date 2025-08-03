package com.codingnomads.ioc.examples.setterinjection;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Disk {
    private String name;
    private Integer memSize;
}
