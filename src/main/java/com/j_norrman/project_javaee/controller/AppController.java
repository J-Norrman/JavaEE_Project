package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.authorities.UserRole;
import com.j_norrman.project_javaee.model.CustomUser;
import com.j_norrman.project_javaee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Controller
public class AppController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("weatherData", null);
        model.addAttribute("forecastData", null);
        

        System.out.println("Accessing the home page");
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("Current authenticated user: " + username);
        } else {
            System.out.println("No authenticated user found.");
        }
        return "index";
    }
    @GetMapping("/register")
    public String register(@RequestParam(required = false) String success,
                           @RequestParam(required = false) String error,
                           Model model) {
        System.out.println("Accessing the register page.");

        // Check if registration was successful
        if ("true".equals(success)) {
            model.addAttribute("registrationSuccess", true);
            System.out.println("Registration was successful.");
        }
        // Check if there was a registration error
        else if ("true".equals(error)) {
            model.addAttribute("registrationError", true);
            System.out.println("Registration failed.");
        }

        return "register";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String logout, Model model, String success) {
        System.out.println("Accessing the login page.");
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            System.out.println("User already logged in. Redirecting to home page.");
            return "redirect:/";
        }
        if ("true".equals(logout)) {
            model.addAttribute("loggedOut", true);
            System.out.println("User has successfully logged out.");
        } else if ("false".equals(logout)) {
            model.addAttribute("loggedOut", false);
            System.out.println("Unable to log out, please log in.");
        }
        if ("true".equals(success)) {
            model.addAttribute("registrationSuccess", true);
            System.out.println("Registration was successful, displaying success message.");
        }
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("Current authenticated user: " + username);
        } else {
            System.out.println("No authenticated user found.");
        }
        return "login";
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        System.out.println("Accessing the logout page.");
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("Current authenticated user: " + username);
        }
        return "logout";
    }
}
