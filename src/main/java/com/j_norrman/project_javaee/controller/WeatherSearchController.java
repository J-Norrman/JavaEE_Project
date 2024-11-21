package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.model.dto.WeatherDataDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherSearchController {

    private final RestTemplate restTemplate;

    public WeatherSearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("weatherData", null);
        model.addAttribute("forecastData", null);
        return "index";
    }

    @GetMapping("/weather/search")
    public String searchWeather(@RequestParam("city") String city, Model model) {
        WeatherDataDTO weatherData = fetchWeatherData(city);
        model.addAttribute("weatherData", weatherData);
        model.addAttribute("forecastData", null); // Ensure forecastData is null
        return "index";
    }

    private WeatherDataDTO fetchWeatherData(String city) {
        String weatherApiUrl = "http://localhost:8081/weather?city=" + city;
        System.out.println("Calling WeatherApp API with URL: " + weatherApiUrl);
        try {
            WeatherDataDTO weatherData = restTemplate.getForObject(weatherApiUrl, WeatherDataDTO.class);
            assert weatherData != null;
            System.out.println("Received weather data for city name: " + weatherData.getCity_name());
            return weatherData;
        } catch (Exception e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
            return null; // Return null if there's an error
        }
    }
}
