package robsunApi.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class FactService {

    public static String getAFact() {
        String url = System.getenv("FACT_URL");
        String key = System.getenv("KEY");
        String header = System.getenv("HEADER");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.addHeader(header, key);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    System.out.println("Failed to fetch a fact. HTTP error code: " + statusCode);
                    return "Failed to fetch a fact. HTTP error code: " + statusCode;
                }

                String responseBody = EntityUtils.toString(response.getEntity());
                JSONArray jsonArray = new JSONArray(responseBody);
                if (!jsonArray.isEmpty()) {
                    JSONObject factObject = jsonArray.getJSONObject(0);

                    return factObject.getString("fact");
                } else {
                    System.out.println("No facts found.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

}
