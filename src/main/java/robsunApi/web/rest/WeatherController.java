package robsunApi.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.domain.model.CityRequest;

import static robsunApi.service.WeatherService.getWeatherInfo;

@RestController
public class WeatherController {

    @PostMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestBody CityRequest cityRequest) {
        String city = cityRequest.getCity();
        String weatherData = getWeatherInfo(city);
        return ResponseEntity.ok(weatherData);
    }
}


