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

import java.util.HashMap;

@Service
public class HobbyGenerator {

    public static HashMap<String, String> giveIdeaForHobby() {
        String url = System.getenv("HOBBY_URL");
        String key = System.getenv("KEY");
        String header = System.getenv("HEADER");
        final Logger log = LoggerFactory.getLogger(HobbyGenerator.class);


        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.addHeader(header, key);
            log.info("Getting random idea for hobby");

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                String responseBody = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = new JSONObject(responseBody);

                String hobby = jsonObject.getString("hobby");
                String hobbyUrl = jsonObject.getString("link");
                HashMap<String, String> hobbyMap = new HashMap<>();
                hobbyMap.put(hobby, hobbyUrl);

                return hobbyMap;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new HashMap<>();
    }
}
