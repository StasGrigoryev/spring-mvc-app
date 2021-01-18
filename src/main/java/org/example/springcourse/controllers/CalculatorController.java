package org.example.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    //here will be my calculator method
    @PostMapping("/calculator")
    public String calculate(@RequestParam(value = "a", required = true) String a,
                            @RequestParam(value = "b", required = true) String b,
                            @RequestParam(value = "operation", required = true)
                                        String operation, Model model) {
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
                result = (float) first / second;
                break;
            default:
                result = -1;
                break;
        }
        model.addAttribute("result", result);
        return "/main/calculator";
    }

    @GetMapping("/calculator")
    public String calculator() {
        return "/main/calculator";
    }
}
