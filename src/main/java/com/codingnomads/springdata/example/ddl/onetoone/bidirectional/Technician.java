package com.codingnomads.springdata.example.ddl.onetoone.bidirectional;

import jakarta.persistence.*;

@Entity
@Table(name = "technicians")
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, updatable = false)
    private String name;

    @OneToOne
    private Car car;
}