package robsunApi.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FactService {

    public static HashMap<String, String> getAFact() {
        String url = System.getenv("FACT_URL");
        String key = System.getenv("KEY");
        String header = System.getenv("HEADER");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.addHeader(header, key);

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                String responseBody = EntityUtils.toString(response.getEntity());


                JSONArray jsonArray = new JSONArray(responseBody);
                if (!jsonArray.isEmpty()) {
                    JSONObject factObject = jsonArray.getJSONObject(0);

                    String fact = factObject.getString("fact");

                    HashMap<String, String> factMap = new HashMap<>();
                    factMap.put("fact", fact);

                    return factMap;
                } else {
                    System.out.println("No facts found.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }

}
