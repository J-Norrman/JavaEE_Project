package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.model.dto.WeatherDataDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testSearchWeather() throws Exception {
        String city = "Stockholm";

        WeatherDataDTO simulatedWeather = new WeatherDataDTO();
        simulatedWeather.setCity_name(city);
        simulatedWeather.setTemp(5.0);
        simulatedWeather.setSunrise("07:30");
        simulatedWeather.setSunset("15:45");
        simulatedWeather.setPrecip(0.1);

        when(restTemplate.getForObject("http://weatherapp:8081/weather?city=" + city, WeatherDataDTO.class))
                .thenReturn(simulatedWeather);

        mockMvc.perform(get("/weather/search?city=" + city))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("weatherData", Matchers.notNullValue()))
                .andExpect(model().attribute("forecastData", Matchers.nullValue()))
                .andExpect(model().attribute("weatherData", Matchers.hasProperty("city_name", Matchers.is(city))))
                .andExpect(model().attribute("weatherData", Matchers.hasProperty("temp", Matchers.is(5.0))));
    }
}