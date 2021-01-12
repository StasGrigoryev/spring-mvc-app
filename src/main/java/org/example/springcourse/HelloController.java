package org.example.springcourse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/hello";
    }
}
