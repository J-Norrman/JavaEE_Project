package com.j_norrman.project_javaee.service;

import com.j_norrman.project_javaee.authorities.UserRole;
import com.j_norrman.project_javaee.model.CustomUser;
import com.j_norrman.project_javaee.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomUser createUser(String username, String password, UserRole role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User already exists with username: " + username);
        }
        CustomUser newUser = new CustomUser(username, password, role);
        return userRepository.save(newUser);
    }
    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
