package com.example.OstadAssignment28.service;

import com.example.OstadAssignment28.dto.WeatherResponse;

public interface WeatherService {

    WeatherResponse getWeather(String city);

}