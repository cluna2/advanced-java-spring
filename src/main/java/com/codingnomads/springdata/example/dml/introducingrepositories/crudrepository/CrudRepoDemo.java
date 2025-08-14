/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.introducingrepositories.crudrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class CrudRepoDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudRepoDemo.class);
    }

    // autowire the UserRepo into the class to gain access to the CRUD methods
    @Autowired
    UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        // create new user
        User user =
                User.builder().firstName("Bobby").lastName("Bobbert").age(56).build();
        User user2 =
                User.builder().firstName("Joanne").lastName("Joanna").age(36).build();

        // save user and assign what is returned to the user variable.
        user = userRepo.save(user);
        user2 = userRepo.save(user2);

        Iterable<User> users = userRepo.findAll();

        for (User u : users) {
            System.out.println(u.toString());
        }

        // delete the user using the id of the inserted user object
        userRepo.deleteById(user.getId());
        userRepo.deleteById(user2.getId());

        Iterable<User> usersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User u = User.builder().firstName("User#").lastName(String.valueOf(i)).age(i).build();
            ((ArrayList<User>) usersList).add(u);
        }

        usersList = userRepo.saveAll(usersList);
        Iterable<User> retrievedUsers = userRepo.findAllById(
                ((ArrayList<User>) usersList).stream().map(u -> u.getId()).collect(Collectors.toList())
        );
        for (User u : retrievedUsers) {
            System.out.println(u.toString());
        }
    }
}
