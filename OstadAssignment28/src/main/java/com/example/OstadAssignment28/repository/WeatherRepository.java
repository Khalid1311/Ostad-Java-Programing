package com.example.OstadAssignment28.repository;

import com.example.OstadAssignment28.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findByCityIgnoreCase(String city);
}