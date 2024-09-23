package robsunApi.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class HobbyGenerator {
    private static final String HOBBY_API_URL = "";

    public static HashMap<String, String> giveIdeaForHobby() {
        String url = System.getenv("HOBBY_URL");
        String key = System.getenv("KEY");
        String header = System.getenv("HEADER");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.addHeader(header, key);

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
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }
}
