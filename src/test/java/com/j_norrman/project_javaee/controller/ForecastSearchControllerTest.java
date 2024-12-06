package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.model.dto.ForecastDTO;
import com.j_norrman.project_javaee.model.dto.ForecastDataDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ForecastSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void searchForecast() throws Exception {
        String city = "Stockholm";

        ForecastDTO simulatedForecast = new ForecastDTO();
        simulatedForecast.setTemp(10.0);
        simulatedForecast.setValid_date("2024-12-06");
        simulatedForecast.setDay_of_week("Friday");

        ForecastDataDTO simulatedForecastData = new ForecastDataDTO();
        simulatedForecastData.setCity_name(city);
        simulatedForecastData.setForecasts(List.of(simulatedForecast));

        Mockito.when(restTemplate.getForObject(
                "http://weatherapp:8081/forecast?city=" + city,
                ForecastDataDTO.class
        )).thenReturn(simulatedForecastData);

        mockMvc.perform(get("/forecast/search?city=" + city))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("forecastData", Matchers.notNullValue()))
                .andExpect(model().attribute("weatherData", Matchers.nullValue()));
    }
}