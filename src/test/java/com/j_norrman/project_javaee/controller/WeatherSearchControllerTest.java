package com.j_norrman.project_javaee.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSearchWeather() throws Exception {
        String city = "Stockholm";
        mockMvc.perform(get("/weather/search?city=" + city))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("weatherData", Matchers.notNullValue()))
                .andExpect(model().attribute("forecastData", Matchers.nullValue()));
    }
}