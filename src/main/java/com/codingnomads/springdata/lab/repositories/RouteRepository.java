package com.codingnomads.springdata.lab.repositories;

import com.codingnomads.springdata.lab.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {


}
