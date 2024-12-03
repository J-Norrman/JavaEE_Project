package com.j_norrman.project_javaee.controller;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("weatherData", Matchers.nullValue()))
                .andExpect(model().attribute("forecastData", Matchers.nullValue()));
    }
    @Test
    public void testRegisterPageAccess() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeDoesNotExist("registrationError"));
    }

    @Test
    public void testLoginPageAccess() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeDoesNotExist("loginError"));
    }
    @Test
    public void testLogin() throws Exception {
        String username = "test";
        String password = "test";
        mockMvc.perform(formLogin("/login").user(username).password(password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}