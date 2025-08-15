/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.lifecyclecallback;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PrintEntity {

    @Id
    @GeneratedValue
    private Long id;

    // write your methods here

    @PrePersist
    private void preSave() {
        System.out.println("Printing before saving to db with id: " + String.valueOf(id));
    }

    @PreUpdate
    private void preUpdate() {
        System.out.println("Printing before an update in db with id: ." + String.valueOf(id));
    }

    @PostLoad
    private void postLoad() {
        System.out.println("Printing after loading from db with id: " + String.valueOf(id));
    }

    @PostRemove
    private void postRemove() {
        System.out.println("Printing after deleting from db with id: " + String.valueOf(id));
    }

}
