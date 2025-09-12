/* CodingNomads (C)2024 */
package com.codingnomads.springtest.usingmockmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Bobbert");
        return "greeting";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String greet() {
        return "Hello Back";
    }

    @GetMapping("/test1")
    public String test1(Model model) {
        model.addAttribute("attribute1", "someValue");
        model.addAttribute("name", "Name1");
        return "greeting";
    }

    @GetMapping("/google")
    public String redirectToGoogle() {
        return "redirect:http://google.com";
    }

    @PostMapping("/non-empty")
    @ResponseBody
    public String returnRequestBody(@RequestBody String body) {
        return body;
    }
}
