/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.DELETE;

import com.codingnomads.springweb.resttemplate.DELETE.models.ResponseObject;
import com.codingnomads.springweb.resttemplate.DELETE.models.Task;
import com.codingnomads.springweb.resttemplate.DELETE.models.User;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class DeleteMain {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DeleteMain.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
//            Task newTask = Task.builder()
//                    .name("should be deleted")
//                    .description("used in a delete RestTemplate example. If you see this something went wrong. Oops")
//                    // be sure to enter a valid user id
//                    .userId(380)
//                    .completed(false)
//                    .build();
//
//            // POST new task to server
//            ResponseObject responseObject = restTemplate.postForObject(
//                    "http://demo.codingnomads.co:8080/tasks_api/tasks/", newTask, ResponseObject.class);
//
//            // confirm data was returned & avoid NullPointerExceptions
//            if (responseObject == null) {
//                throw new Exception("The server did not return anything. Not even a ResponseObject!");
//            } else if (responseObject.getData() == null) {
//                throw new Exception("The server encountered this error while creating the task:"
//                        + responseObject.getError().getMessage());
//            } else {
//                newTask = responseObject.getData();
//            }
//
//            System.out.println("The task was successfully created");
//            System.out.println(newTask);
//
//            // delete the newTask using the ID the server returned
//            restTemplate.delete("http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId());
//            System.out.println("The task was also successfully deleted");
//
//            // try to GET, verify record was deleted
//            try {
//                restTemplate.getForEntity(
//                        "http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId(), ResponseObject.class);
//            } catch (HttpClientErrorException e) {
//                System.out.println(e.getMessage());
//            }
//
//            // delete using exchange()
//            HttpEntity<Task> httpEntity = new HttpEntity<>(newTask);
//            try {
//                restTemplate.exchange(
//                        "http://demo.codingnomads.co:8080/tasks_api/tasks/" + newTask.getId(),
//                        HttpMethod.DELETE,
//                        httpEntity,
//                        ResponseObject.class);
//            } catch (HttpClientErrorException e) {
//                System.out.println(e.getMessage());
//            }

            User userToDelete = User.builder()
                    .email("deletedEmail7@email.com")
                    .firstName("deleted")
                    .lastName("deleted")
                    .build();
            ResponseObject responseObject = restTemplate.postForObject(
                    "http://demo.codingnomads.co:8080/tasks_api/users/", userToDelete, ResponseObject.class);

            // confirm user was created and retrieved successfully
            if (responseObject == null) {
                throw new Exception("The server did not return anything. Not even a ResponseObject!");
            } else if (responseObject.getData() == null) {
                throw new Exception("The server encountered this error while creating the user:"
                        + responseObject.getError().getMessage());
            } else {
                userToDelete = responseObject.getData();
            }


            System.out.println(userToDelete.toString());

            restTemplate.delete(
                    "http://demo.codingnomads.co:8080/tasks_api/users/" + userToDelete.getId());

            try {
                restTemplate.getForEntity(
                        "http://demo.codingnomads.co:8080/tasks_api/users/" + userToDelete.getId(), ResponseObject.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            // rePOST user
            HttpEntity<User> entity = new HttpEntity<>(
                    User.builder()
                        .email("deletedEmail7@email.com")
                        .firstName("deleted")
                        .lastName("deleted")
                        .build());
            ResponseEntity<ResponseObject> responseObj = restTemplate.exchange(
                    "http://demo.codingnomads.co:8080/tasks_api/users/",
                    HttpMethod.POST,
                    entity,
                    ResponseObject.class);



            userToDelete = responseObj.getBody().getData();
            restTemplate.delete("http://demo.codingnomads.co:8080/tasks_api/users/" + userToDelete.getId());

            try {
                restTemplate.getForEntity(
                        "http://demo.codingnomads.co:8080/tasks_api/users/" + userToDelete.getId(), ResponseObject.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            List<User> usersToDelete = List.of(
                    User.builder().id(14676L).build(),
                    User.builder().id(14679L).build(),
                    User.builder().id(14682L).build(),
                    User.builder().id(14685L).build(),
                    User.builder().id(14692L).build()
            );
            String url = "http://demo.codingnomads.co:8080/tasks_api/users/";
            for (User user: usersToDelete) {
                HttpEntity<User> userEntity = new HttpEntity<>(user);
                try {
                    restTemplate.exchange(
                            url + user.getId(),
                            HttpMethod.DELETE,
                            userEntity,
                            ResponseObject.class
                    );
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        };
    }
}
