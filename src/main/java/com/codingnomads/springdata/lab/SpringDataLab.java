/* CodingNomads (C)2024 */
package com.codingnomads.springdata.lab;

import com.codingnomads.springdata.lab.models.Area;
import com.codingnomads.springdata.lab.models.Route;
import com.codingnomads.springdata.lab.models.RouteNetwork;
import com.codingnomads.springdata.lab.repositories.AreaRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.codingnomads.springdata.lab.repositories.RouteNetworkRepository;
import com.codingnomads.springdata.lab.repositories.RouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataLab implements CommandLineRunner {

    private final AreaRepository areaRepository;

    private final RouteRepository routeRepository;

    private final RouteNetworkRepository routeNetworkRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataLab.class);
    }

    @Override
    @Transactional
    // use Transactional annotation to keep objects from being detached in persistence context
    public void run(String... args) throws Exception {

        if (areaRepository.findAll().isEmpty()) {
            final List<Area> areas = areaRepository.saveAll(Arrays.asList(
                    Area.builder().code("G").build(),
                    Area.builder().code("H").build(),
                    Area.builder().code("Y").build(),
                    Area.builder().code("Z").build(),
                    Area.builder().code("A").build(),
                    Area.builder().code("B").build(),
                    Area.builder().code("C").build(),
                    Area.builder().code("D").build()
            ));
            System.out.println("Printing saved areas");
            areas.forEach(System.out::println);

            Area cArea = areaRepository.getAreaByCode("C");
            System.out.println("CArea = " + cArea.toString());

        }


        if (routeRepository.findAll().isEmpty()) {
            List<Route> routeList = new ArrayList<>();
            Route yToZRoute = Route.
                    builder().
                    origin(areaRepository.getAreaByCode("Y")).
                    destination(areaRepository.getAreaByCode("Z")).
                    build();
            routeList.add(yToZRoute);
            Route aToBRoute = Route.
                    builder().
                    origin(areaRepository.getAreaByCode("A")).
                    destination(areaRepository.getAreaByCode("B")).
                    build();
            routeList.add(aToBRoute);
            Route cToGRoute = Route.
                    builder().
                    origin(areaRepository.getAreaByCode("C")).
                    destination(areaRepository.getAreaByCode("G")).
                    build();
            routeList.add(cToGRoute);
            Route cToBRoute = Route.
                    builder().
                    origin(areaRepository.getAreaByCode("C")).
                    destination(areaRepository.getAreaByCode("B")).
                    build();
            routeList.add(cToBRoute);
            routeList = routeRepository.saveAllAndFlush(routeList);
            System.out.println("Printing saved routes");
            routeList.forEach(System.out::println);

            List<Route> routesOriginatingAtC = routeRepository.getRouteByOrigin_code("C");
            System.out.println("All routes starting at C");
            routesOriginatingAtC.forEach(System.out::println);

            List<Route> routesEndingAtB = routeRepository.getRouteByDestination_code("B");
            System.out.println("All routes ending at B");
            routesEndingAtB.forEach(System.out::println);
        }


        if (routeNetworkRepository.findAll().isEmpty()) {
            final List<RouteNetwork> routeNetworks = routeNetworkRepository.saveAll(Arrays.asList(
                    RouteNetwork.builder().area(areaRepository.getAreaByCode("A")).build(),
                    RouteNetwork.builder().area(areaRepository.getAreaByCode("B")).build(),
                    RouteNetwork.builder().area(areaRepository.getAreaByCode("C")).build()
            ));

            System.out.println("Printing saved networks");
            routeNetworks.forEach(System.out::println);
        }
        routeNetworkRepository.deleteAll();
        routeRepository.deleteAll();
        areaRepository.deleteAll();

    }
}
