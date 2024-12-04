package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.authorities.UserRole;
import com.j_norrman.project_javaee.model.CustomUser;
import com.j_norrman.project_javaee.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/createDebugUser")
    @ResponseBody
    public CustomUser createDebugUser() {
        return userService.createUser(
                "Admin",
                passwordEncoder.encode("123"),
                UserRole.ADMIN
        );
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password) {
        try {
            userService.createUser(username, passwordEncoder.encode(password), UserRole.USER);
            System.out.println("Registration successful, redirecting to /register with success flag.");
            return "redirect:/login?success=true";
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed, redirecting to /register with error flag.");
            return "redirect:/register?error=true";
        }
    }
}
