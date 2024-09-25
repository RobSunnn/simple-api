package robsunApi.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import robsunApi.domain.model.CityRequest;
import robsunApi.service.TokenService;

import static robsunApi.service.QuoteService.randomQuote;
import static robsunApi.service.WeatherService.getWeatherInfo;

@RestController
public class WeatherController {
    private final TokenService tokenService;

    public WeatherController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestHeader("x-api-token") String token, @RequestBody CityRequest cityRequest) {
        if (tokenService.isValidToken(token)) {
            // Proceed with request processing if token is valid
            String city = cityRequest.getCity();
            String weatherData = getWeatherInfo(city);
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}


