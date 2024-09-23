package robsunApi.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static robsunApi.service.WeatherService.getWeatherInfo;

@RestController
public class WeatherController {

    @GetMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        // Call the getWeatherInfo method from the WeatherService class
        String weatherData = getWeatherInfo(city);

        if (weatherData != null) {
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.status(500).body("Failed to fetch weather data");
        }
    }
}
