package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.authorities.UserRole;
import com.j_norrman.project_javaee.model.CustomUser;
import com.j_norrman.project_javaee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

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
