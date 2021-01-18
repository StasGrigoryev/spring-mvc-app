package org.example.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MainController {



    @GetMapping("/hello")
    public String greeting(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "lastname", required = false) String lastname,
                           Model model) {
        // I know it looks horrible but it checks if the name and the lastname were filled
        String userName = name;
        String userLastname = lastname;
        String greeting = "Hello, ";
        if (!(userName == null && userLastname == null)) {
            if (userName == null) greeting += userLastname + "!";
            else if (userLastname == null) greeting += userName + "!";
            else greeting += userName + " " + userLastname + "!";
        } else greeting += "user!";
        model.addAttribute("greeting", greeting);
        return "main/greeting";
    }

    @GetMapping("/bye")
    public String goodbye(HttpServletRequest req) {

        return "main/goodbye";
    }

    @GetMapping("/")
    public String main() {
        return "main/main";
    }
}
