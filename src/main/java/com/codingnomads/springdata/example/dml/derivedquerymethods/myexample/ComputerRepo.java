package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputerRepo extends JpaRepository<Computer, Long> {

    // Names

    List<Computer> findByName(String name);
    int countByNameIs(String name);
    List<Computer> findByNameStartingWith(String namePrefix);
    List<Computer> findByNameContains(String pattern);
    List<Computer> findFirst5ByNameEndingWith(String nameSuffix);


    // Num Cores

    List<Computer> findByNumCoresIs(int numCores);
    List<Computer> getByNumCoresLessThan(int numCores);
    List<Computer> getByNumCoresLessThanEqualAndNumCoresGreaterThanEqual(int upperCores, int lowerCores);

    // Brand
    Page<Computer> queryByBrandIsNot(String brand, Pageable pageable);
    List<Computer> readLast20ByBrandIs(String brand);
    List<Computer> findByBrand(String brand);

    // 64-Bit
    List<Computer> findBySixtyFourBit(boolean sixtyFourBit);

}
