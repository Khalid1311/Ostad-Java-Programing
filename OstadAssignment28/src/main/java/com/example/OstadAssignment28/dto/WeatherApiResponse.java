package com.example.OstadAssignment28.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiResponse {

    private LocationDto location;

    private CurrentDto current;

}