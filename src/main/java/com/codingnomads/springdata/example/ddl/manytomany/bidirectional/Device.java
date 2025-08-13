package com.codingnomads.springdata.example.ddl.manytomany.bidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "devices")
    private List<User> users;
}
