package com.j_norrman.project_javaee.controller;
import com.j_norrman.project_javaee.model.CustomUser;
import com.j_norrman.project_javaee.model.CustomUserDetails;
import com.j_norrman.project_javaee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    private final DaoAuthenticationProvider authenticationProvider;

    private final UserService userService;

    public AppController(DaoAuthenticationProvider authenticationProvider, UserService userService) {
        this.authenticationProvider = authenticationProvider;
        this.userService = userService;
    }

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
        if ("true".equals(success)) {
            model.addAttribute("registrationSuccess", true);
            System.out.println("Registration was successful.");
        }
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
    @GetMapping("/account")
    public String Account(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<CustomUser> users= userService.getAllUsers();

        System.out.println("Accessing the Account page.");
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
            model.addAttribute("username", user.getUsername());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("authority", user.getAuthorities());
            model.addAttribute("users", users);
            System.out.println("Current authenticated user: " + username);
        }
        return "account";
    }

    @GetMapping("/account/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        System.out.println("Attempting to delete user: " + id);
        userService.deleteUser(id);
        System.out.println("User with ID: " + id + " has successfully deleted.");
        return "redirect:/account";
    }
}
