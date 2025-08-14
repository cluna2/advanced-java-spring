package com.codingnomads.springdata.example.ddl.joincolumn;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Entity1")
@NoArgsConstructor
@Getter
@Setter
public class Entity1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            @JoinColumn(
                    name = "user_name",
                    referencedColumnName = "name"
            )
    })
    private User user;
}
