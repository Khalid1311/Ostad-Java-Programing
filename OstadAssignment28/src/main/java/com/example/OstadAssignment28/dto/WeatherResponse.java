package com.example.OstadAssignment28.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponse {

    @NotBlank(message = "City is required")
    private String city;

    private Double latitude;

    private Double longitude;

    private Double temperature;

    private Double wind;

    private Integer humidity;
}