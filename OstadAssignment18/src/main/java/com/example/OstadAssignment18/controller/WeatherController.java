package com.example.OstadAssignment18.controller;

import com.example.OstadAssignment18.service.GreetingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final GreetingService greetingService;

    @Value("${app.name}")
    private String appName;

    public WeatherController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/weather")
    public String getWeather() {
        return appName + " : " + greetingService.getMessage();
    }
}
