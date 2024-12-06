package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.model.dto.ForecastDataDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ForecastSearchController {

    private final RestTemplate restTemplate;

    public ForecastSearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/forecast/search")
    public String searchForecast(@RequestParam("city") String city, Model model) {
        ForecastDataDTO forecastData = fetchForecastData(city);
        model.addAttribute("forecastData", forecastData);
        model.addAttribute("weatherData", null);
        return "index";
    }

    private ForecastDataDTO fetchForecastData(String city) {
        String forecastApiUrl = "http://weatherapp:8081/forecast?city=" + city;
        System.out.println("Calling WeatherApp API for forecast with URL: " + forecastApiUrl);
        try {
            ForecastDataDTO forecastData = restTemplate.getForObject(forecastApiUrl, ForecastDataDTO.class);
            assert forecastData != null;
            System.out.println("Received forecast data for city name: " + forecastData.getCity_name());
            return forecastData;
        } catch (Exception e) {
            System.err.println("Error fetching forecast data: " + e.getMessage());
            return null;
        }
    }
}
