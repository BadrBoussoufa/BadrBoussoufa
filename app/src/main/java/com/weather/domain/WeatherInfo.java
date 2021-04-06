package com.weather.domain;

public class WeatherInfo {
    private String zipCode;
    private String forecast;
    private Integer temperature;
    
    public WeatherInfo(String zipCode, String forecast, Integer temperature) {
        this.zipCode = zipCode;
        this.forecast = forecast;
        this.temperature = temperature;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getForecast() {
        return forecast;
    }

    public String getZipCode() {
        return zipCode;
    }

    
}
