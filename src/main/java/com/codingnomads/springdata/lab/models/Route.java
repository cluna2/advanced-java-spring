/* CodingNomads (C)2024 */
package com.codingnomads.springdata.lab.models;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "routes")
@ToString
public class Route implements Serializable {

    private static final long serialVersionUID = -2624055642258734917L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_area_id", nullable = false, foreignKey = @ForeignKey(name = "fk_routes_origin_area_id"))
    private Area origin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "destination_area_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_routes_destination_area_id"))
    private Area destination;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_id", nullable = false, foreignKey = @ForeignKey(name = "fk_network_area_id"))
    private RouteNetwork routeNetwork;



    @Builder
    public Route(Area origin, Area destination) {
        this.origin = origin;
        this.destination = destination;
        this.code = this.origin.getCode() + "-" + this.destination.getCode();
    }
}
