/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.springdatajdbc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringDataJDBCDemo implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJDBCDemo.class);
    }

    @Override
    public void run(String... strings) {

        try {
            // create employee table using the JdbcTemplate method "execute"
            jdbcTemplate.execute("CREATE TABLE employees (id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(255) NOT NULL,last_name  VARCHAR(255) NOT NULL);");
            jdbcTemplate.execute("CREATE TABLE chairs (id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,height INTEGER NOT NULL);");
        } catch (Exception e) {
            // nothing
            System.out.println("Chairs table failed to create");
            throw new RuntimeException(e.getCause());
        }

        // create a list of first and last names
        List<Object[]> splitUpNames = Stream.of("Java Ninja", "Spring Guru", "Java Guru", "Spring Ninja")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // create list of chair brand names and heights
        List<Object[]> chairs = Stream.of("Herman Miller, 15", "Steelcase, 17", "SecretLab, 19", "Eurotech, 17")
                .map(chair -> chair.split(", "))
                .collect(Collectors.toList());

        // for each first & last name pair insert an Employee into the database
        for (Object[] name : splitUpNames) {
            jdbcTemplate.execute(
                    String.format("INSERT INTO employees(first_name, last_name) VALUES ('%s','%s')", name[0], name[1]));
        }

        // for each chair name & height pair, insert a Chair into the db
        for (Object[] chair: chairs) {
            jdbcTemplate.execute(
                    String.format("INSERT INTO chairs(name, height) VALUES ('%s', '%s')", chair[0], chair[1])
            );
        }

        // query the database for Employees with first name Java
        jdbcTemplate
                .query(
                        "SELECT id, first_name, last_name FROM employees WHERE first_name = 'Java'",
                        (rs, rowNum) ->
                                new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")))
                // print each found employee to the console
                .forEach(employee -> System.out.println(employee.toString()));

        jdbcTemplate
                .query(
                        "SELECT id, name, height FROM chairs WHERE height = 17",
                        (rs, rowNum) -> new Chair(rs.getLong("id"), rs.getString("name"), rs.getInt("height"))
                )
                .forEach(chair -> System.out.println(chair.toString()));
        // truncate the table
        jdbcTemplate.execute("TRUNCATE TABLE employees;");
        jdbcTemplate.execute("TRUNCATE TABLE chairs");
        // delete the table
        jdbcTemplate.execute("DROP TABLE employees");
        jdbcTemplate.execute("DROP TABLE chairs");
    }
}
