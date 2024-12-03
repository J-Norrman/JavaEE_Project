package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.authorities.UserRole;
import com.j_norrman.project_javaee.model.CustomUser;
import com.j_norrman.project_javaee.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreateDebugUser() throws Exception {
        CustomUser mockUser = new CustomUser("Admin", "encodedPassword", UserRole.ADMIN);
        when(passwordEncoder.encode("123")).thenReturn("encodedPassword");
        when(userService.createUser("Admin", "encodedPassword", UserRole.ADMIN)).thenReturn(mockUser);

        mockMvc.perform(get("/createDebugUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Admin"))
                .andExpect(jsonPath("$.password").value("encodedPassword"))
                .andExpect(jsonPath("$.role").value("ADMIN"));

        verify(passwordEncoder, times(1)).encode("123");
        verify(userService, times(1)).createUser("Admin", "encodedPassword", UserRole.ADMIN);
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post("/register").with(csrf()).param("username", "createTest")
                        .param("password","passwordTest"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?success=true"));
    }
}