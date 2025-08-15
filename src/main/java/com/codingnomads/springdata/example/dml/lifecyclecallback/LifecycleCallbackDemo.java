/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.lifecyclecallback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LifecycleCallbackDemo {

    public static void main(String[] args) {
        SpringApplication.run(LifecycleCallbackDemo.class);
    }

    @Bean
    public CommandLineRunner runStuff(PrintEntityRepository printEntityRepository) {
        return (args) -> {
            // put your logic here
            PrintEntity p1 = new PrintEntity();
            PrintEntity p2 = new PrintEntity();
            PrintEntity p3 = new PrintEntity();

            printEntityRepository.saveAll(List.of(p1, p2, p3));

            Iterable<PrintEntity> entitiesIterable = printEntityRepository.findAll();

            printEntityRepository.deleteAll(entitiesIterable);

        };
    }
}
