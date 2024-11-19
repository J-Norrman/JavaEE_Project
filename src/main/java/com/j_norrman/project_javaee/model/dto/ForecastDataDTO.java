package com.j_norrman.project_javaee.model.dto;

import java.util.List;

public class ForecastDataDTO {
    private String city_name;
    private List<ForecastDTO> forecasts;

    public ForecastDataDTO() {
    }

    public ForecastDataDTO(String city_name, List<ForecastDTO> forecasts) {
        this.city_name = city_name;
        this.forecasts = forecasts;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public List<ForecastDTO> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastDTO> forecasts) {
        this.forecasts = forecasts;
    }
}
