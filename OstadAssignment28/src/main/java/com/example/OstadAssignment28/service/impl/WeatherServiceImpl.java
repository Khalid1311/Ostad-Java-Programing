package com.example.OstadAssignment28.service.impl;

import com.example.OstadAssignment28.config.WeatherProperties;
import com.example.OstadAssignment28.dto.WeatherApiResponse;
import com.example.OstadAssignment28.dto.WeatherResponse;
import com.example.OstadAssignment28.entity.Weather;
import com.example.OstadAssignment28.repository.WeatherRepository;
import com.example.OstadAssignment28.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final WeatherProperties weatherProperties;

    @Override
    public WeatherResponse getWeather(String city) {

        Optional<Weather> optionalWeather =
                weatherRepository.findByCityIgnoreCase(city);

        if (optionalWeather.isPresent()) {

            Weather weather = optionalWeather.get();

            long seconds = Duration.between(
                    weather.getLastFetchedTime(),
                    LocalDateTime.now()
            ).getSeconds();

            if (seconds < 60) {
                return mapToResponse(weather);
            }

            return updateWeather(weather);
        }

        return fetchAndSave(city);

    }

    private WeatherResponse fetchAndSave(String city) {

        String url = weatherProperties.getUrl()
                + "?key=" + weatherProperties.getKey()
                + "&q=" + city;

        WeatherApiResponse apiResponse =
                restTemplate.getForObject(url, WeatherApiResponse.class);

        assert apiResponse != null;
        Weather weather = Weather.builder()
                .city(apiResponse.getLocation().getName())
                .latitude(apiResponse.getLocation().getLat())
                .longitude(apiResponse.getLocation().getLon())
                .temperature(apiResponse.getCurrent().getTempC())
                .wind(apiResponse.getCurrent().getWindKph())
                .humidity(apiResponse.getCurrent().getHumidity())
                .lastFetchedTime(LocalDateTime.now())
                .build();

        weatherRepository.save(weather);

        return mapToResponse(weather);
    }

    private WeatherResponse updateWeather(Weather weather) {

        String url = weatherProperties.getUrl()
                + "?key=" + weatherProperties.getKey()
                + "&q=" + weather.getCity();

        WeatherApiResponse apiResponse =
                restTemplate.getForObject(url, WeatherApiResponse.class);

        assert apiResponse != null;
        weather.setLatitude(apiResponse.getLocation().getLat());
        weather.setLongitude(apiResponse.getLocation().getLon());
        weather.setTemperature(apiResponse.getCurrent().getTempC());
        weather.setWind(apiResponse.getCurrent().getWindKph());
        weather.setHumidity(apiResponse.getCurrent().getHumidity());
        weather.setLastFetchedTime(LocalDateTime.now());

        weatherRepository.save(weather);

        return mapToResponse(weather);
    }

    private WeatherResponse mapToResponse(Weather weather) {

        return WeatherResponse.builder()
                .city(weather.getCity())
                .latitude(weather.getLatitude())
                .longitude(weather.getLongitude())
                .temperature(weather.getTemperature())
                .wind(weather.getWind())
                .humidity(weather.getHumidity())
                .build();
    }
}
