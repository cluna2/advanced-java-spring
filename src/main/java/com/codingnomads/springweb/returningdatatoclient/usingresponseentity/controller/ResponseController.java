/* CodingNomads (C)2024 */
package com.codingnomads.springweb.returningdatatoclient.usingresponseentity.controller;

import com.codingnomads.springweb.returningdatatoclient.usingresponseentity.model.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResponseController {

    User user = new User(1, "Test User", "test@email.com");

    Map<Integer, User> userMap = new HashMap<>(Map.of(1, user));

    @GetMapping("/constructor")
    public ResponseEntity<User> constructorMethod() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("TEST", "TEST HEADER");
        headers.add("Location", "/users/" + user.getId());
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @GetMapping("/builder")
    public ResponseEntity<User> builderMethod() {
        return ResponseEntity.created(URI.create("/users/" + user.getId()))
                .header("TEST", "TEST HEADER")
                .body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        if (user.getId() == id) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/practice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> practice(@RequestBody User user) throws URISyntaxException {
        if (user.getId() < 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("TEST", "TEST HEADER")
                    .body(null);
        }
        userMap.put(user.getId(), user);
        return ResponseEntity
                .created(new URI("/practice" + user.getId()))
                .header("TEST", "TEST HEADER")
                .body(user);
    }

    @GetMapping("/practice/{id}")
    public ResponseEntity<?> practiceGet(@PathVariable int id) {
        if (userMap.containsKey(id)) {
            return ResponseEntity.ok().body(userMap.get(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
