package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;


import com.codingnomads.corespring.examples.primaryannotation.Processor;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "computers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String brand;

    private Integer numCores;

    @Column(nullable = false)
    private boolean sixtyFourBit;
}
