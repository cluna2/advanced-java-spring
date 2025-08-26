package com.codingnomads.springdata.lab.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "routeNetworks")
@ToString
// network is collection of all routes beginning at area parameter
public class RouteNetwork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String code;

    @OneToOne(optional = false)
    private Area area;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Route> routes;


    @Builder
    public RouteNetwork(Area area) {
        this.area = area;
        this.code = this.area.getCode();
    }

}
