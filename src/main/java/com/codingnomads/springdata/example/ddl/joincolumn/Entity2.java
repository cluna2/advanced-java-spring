package com.codingnomads.springdata.example.ddl.joincolumn;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Entity2")
@NoArgsConstructor
@Getter
@Setter
public class Entity2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "entity2_id", referencedColumnName = "id")
    })
    private List<Entity1> entity1;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "example_id", referencedColumnName = "id")
    })
    private Example example;

}
