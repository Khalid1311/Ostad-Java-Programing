package com.example.OstadAssignment28.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "weather.api")
public class WeatherProperties {

    private String key;
    private String url;

}
