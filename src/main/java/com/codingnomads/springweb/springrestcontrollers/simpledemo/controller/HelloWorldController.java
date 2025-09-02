/* CodingNomads (C)2024 */
package com.codingnomads.springweb.springrestcontrollers.simpledemo.controller;

import com.codingnomads.springweb.springrestcontrollers.simpledemo.models.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class HelloWorldController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello Spring Developer!";
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting(@PathVariable(name = "name") String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping(path = "/number", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> numbers() { return List.of(1, 2, 3, 4, 5); }

    @RequestMapping(path = "/task/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task task(@PathVariable(name = "id") Long id) {
        return Task.builder().id(id).name("task").build();
    }
}
