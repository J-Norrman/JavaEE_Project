package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.authorities.UserRole;
import com.j_norrman.project_javaee.model.CustomUser;
import com.j_norrman.project_javaee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/createDebugUser")
    public CustomUser createDebugUser() {
        return userService.createUser(
                "Bob",
                passwordEncoder.encode("123"),
                UserRole.ADMIN
        );
    }
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(username, passwordEncoder.encode(password), UserRole.USER);
            redirectAttributes.addFlashAttribute("message", "Registration successful! Please log in.");
            System.out.println("Registration successful, redirecting to /login");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            System.out.println("Registration failed, redirecting back to /register");
            return "redirect:/register";
        }
    }
}
