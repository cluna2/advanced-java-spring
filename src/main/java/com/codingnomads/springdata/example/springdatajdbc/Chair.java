package com.codingnomads.springdata.example.springdatajdbc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Chair {
    private final long id;
    private final String name;
    private final int height;
}
