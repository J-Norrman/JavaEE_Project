package com.j_norrman.project_javaee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AppController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("weatherData", null);
        model.addAttribute("forecastData", null);
        return "index";
    }
    @GetMapping("/register")
    public String register(Model model) {

        return "register";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
