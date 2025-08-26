package com.codingnomads.springdata.lab.repositories;

import com.codingnomads.springdata.lab.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> getRouteByOrigin_code(String code);

    List<Route> getRouteByDestination_code(String code);

    List<Route> findAllByCodeContaining(String code);

    Route findRouteByCode(String code);
}
