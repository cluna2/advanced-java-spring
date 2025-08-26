package com.codingnomads.springdata.lab.repositories;

import com.codingnomads.springdata.lab.models.Area;
import com.codingnomads.springdata.lab.models.RouteNetwork;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RouteNetworkRepository extends JpaRepository<RouteNetwork, Long> {

    RouteNetwork findRouteNetworkByCode(String code);

    RouteNetwork findRouteNetworkByArea(Area area);

    @Query(
            value = "SELECT rn FROM RouteNetwork rn " +
                    "WHERE rn.id = (SELECT r.id FROM Route r " +
                            "WHERE r.origin.code = :area OR r.destination.code = :code)"
    )
    List<RouteNetwork> findRouteNetworkByRoutesContainingArea(@Param("code") String code);
}
