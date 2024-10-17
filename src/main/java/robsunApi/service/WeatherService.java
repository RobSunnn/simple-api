package robsunApi.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    public static String getWeatherInfo(String city) {

        String key = System.getenv("WEATHER_KEY");
        final Logger log = LoggerFactory.getLogger(WeatherService.class);

        String weatherUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + city + "?unitGroup=us&key=" + key + "&contentType=json&iconSet=icons2";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(weatherUrl);
            log.info("Getting information for the weather");
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }
}
