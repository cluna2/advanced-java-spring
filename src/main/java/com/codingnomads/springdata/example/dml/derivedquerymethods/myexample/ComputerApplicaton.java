package com.codingnomads.springdata.example.dml.derivedquerymethods.myexample;


import com.codingnomads.springdata.example.dml.derivedquerymethods.plantexample.PlantApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ComputerApplicaton implements CommandLineRunner {

    @Autowired
    ComputerRepo computerRepo;

    public static void main(String[] args) {
        SpringApplication.run(ComputerApplicaton.class);
    }

    @Override
    public void run(String... args) throws Exception {
        String asus = "ASUS";
        String hp = "HP";
        String dell = "Dell";

        Computer desktop1 = Computer.builder()
                .name("desktop1")
                .brand(asus)
                .numCores(16)
                .sixtyFourBit(true)
                .build();

        Computer laptop1 = Computer.builder()
                .name("laptop1")
                .brand(hp)
                .numCores(8)
                .sixtyFourBit(true)
                .build();

        Computer desktop2 = Computer.builder()
                .name("desktop2")
                .brand(dell)
                .numCores(4)
                .sixtyFourBit(false)
                .build();

        Computer desktop3 = Computer
                .builder()
                .name("desktop3")
                .brand(hp)
                .numCores(1)
                .sixtyFourBit(false)
                .build();

        Computer laptop2 = Computer
                .builder()
                .name("laptop2")
                .brand(dell)
                .numCores(16)
                .sixtyFourBit(true)
                .build();

        computerRepo.save(desktop1);
        computerRepo.save(desktop2);
        computerRepo.save(desktop3);
        computerRepo.save(laptop1);
        computerRepo.save(laptop2);

        computerRepo.flush();

        System.out.println("--- Dell computers ---");
        List<Computer> dells = computerRepo.findByBrand(dell);
        dells.forEach(System.out::println);

        System.out.println("--- Non 64 Bit computers --- ");
        List<Computer> non64Bit = computerRepo.findBySixtyFourBit(false);
        non64Bit.forEach(System.out::println);

        System.out.println("--- Computers with 4-8 cores ---");
        List<Computer> mediumCoreComputers = computerRepo.getByNumCoresLessThanEqualAndNumCoresGreaterThanEqual(8, 4);
        mediumCoreComputers.forEach(System.out::println);

        System.out.println("-- DELETING ALL ---");
        computerRepo.deleteAll();
    }

}
