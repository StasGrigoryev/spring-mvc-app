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

//here will be my calculator method
    @PostMapping("/calculator")
    public String calculate(@RequestParam("a") String a,
                            @RequestParam("b") String b,
                            @RequestParam("operation") String operation, Model model) {
        int first = Integer.parseInt(a);
        int second = Integer.parseInt(b);
        float result;
        switch (operation) {
            case "1":
                result = first + second;
                break;
            case "2":
                result = first - second;
                break;
            case "3":
                result = first * second;
                break;
            case "4":
                result = first / second;
                break;
            default:
                result = -1;
                break;
        }
        model.addAttribute("result", result);
        return "redirect:main/calculator";
    }

    @GetMapping("/calculator")
    public String calculator() {
        return "/main/calculator";
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
