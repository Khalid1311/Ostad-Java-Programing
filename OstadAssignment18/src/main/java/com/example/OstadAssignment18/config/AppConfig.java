package com.example.OstadAssignment18.config;

import com.example.OstadAssignment18.service.GreetingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    @Profile("summer")
    public GreetingService summerService(@Value("${app.message}") String message) {
        return new GreetingService(message);
    }

    @Bean
    @Profile("winter")
    public GreetingService winterService(@Value("${app.message}") String message) {
        return new GreetingService(message);
    }
}
