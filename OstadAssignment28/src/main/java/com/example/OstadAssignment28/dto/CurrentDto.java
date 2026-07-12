package com.example.OstadAssignment28.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentDto {

    @JsonProperty("temp_c")
    private Double tempC;

    @JsonProperty("wind_kph")
    private Double windKph;

    private Integer humidity;
}