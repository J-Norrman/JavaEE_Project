package com.j_norrman.project_javaee.controller;

import com.j_norrman.project_javaee.model.dto.ForecastDTO;
import com.j_norrman.project_javaee.model.dto.ForecastDataDTO;
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
        return "index";
    }

    @GetMapping("/weather/search")
    public String searchWeather(@RequestParam("city") String city, Model model) {
        WeatherDataDTO weatherData = fetchWeatherData(city);
        model.addAttribute("weatherData", weatherData);
        return "index";
    }
    @GetMapping("/forecast/search")
    public String searchForecast(@RequestParam("city") String city, Model model) {
        ForecastDataDTO forecastData = fetchForecastData(city);
        model.addAttribute("forecastData", forecastData);
        return "index";
    }

    private WeatherDataDTO fetchWeatherData(String city) {
        String weatherApiUrl = "http://localhost:8081/weather?city=" + city;
        System.out.println("Calling WeatherApp API with URL: " + weatherApiUrl);
        try {
            WeatherDataDTO weatherData = restTemplate.getForObject(weatherApiUrl, WeatherDataDTO.class);
            if (weatherData != null) {
                System.out.println("Received weather data: " + weatherData);
            } else {
                System.out.println("No weather data found for city: " + city);
            }
            return weatherData;
        } catch (Exception e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
            return null; // Return null if there's an error
        }
    }
    private ForecastDataDTO fetchForecastData(String city){
        String forecastApiUrl = "http://localhost:8081/forecast?city=" + city;
        System.out.println("Calling WeatherApp API with URL: " + forecastApiUrl);
        try{
            ForecastDataDTO forecastData = restTemplate.getForObject(forecastApiUrl, ForecastDataDTO.class);
            if (forecastData != null) {
                System.out.println("Received forecast data: " + forecastData);
            }else{
                System.out.println("No forecast data found for city: " + city);
            }return forecastData;
        }catch (Exception e){
            System.out.println("Error fetching forecast data: " + e.getMessage());
            return null;
        }
    }
}
