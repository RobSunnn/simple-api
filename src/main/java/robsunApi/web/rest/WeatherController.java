package robsunApi.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.domain.model.CityRequest;
import robsunApi.service.TokenService;
import robsunApi.util.response.ResponseUtil;

import static robsunApi.service.WeatherService.getWeatherInfo;

@RestController
public class WeatherController {
    private final TokenService tokenService;

    public WeatherController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestHeader("x-api-token") String token, @RequestBody CityRequest cityRequest) {
        if (!tokenService.isValidToken(token)) {
            return ResponseUtil.errorResponse(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        String city = cityRequest.getCity();
        String weatherData = getWeatherInfo(city);
        return ResponseEntity.ok(weatherData);
    }
}


