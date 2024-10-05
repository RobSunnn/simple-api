package robsunApi.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import robsunApi.domain.model.CityRequest;
import robsunApi.service.TokenService;

import static robsunApi.service.WeatherService.getWeatherInfo;

@RestController
public class WeatherController {
    private final TokenService tokenService;

    public WeatherController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestBody CityRequest cityRequest) {
        String city = cityRequest.getCity();
        String weatherData = getWeatherInfo(city);
        return ResponseEntity.ok(weatherData);
    }
}


