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
public class QuoteService {

    public static HashMap<String, String> randomQuote() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = System.getenv("URL");
            String key = System.getenv("KEY");
            String header = System.getenv("HEADER");
            System.out.println(url +" " + key + " " +header);

            HttpGet request = new HttpGet(url);
            request.addHeader(header, key);

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                String responseBody = EntityUtils.toString(response.getEntity());
                JSONArray jsonArray = new JSONArray(responseBody);
                System.out.println(jsonArray);
                if (!jsonArray.isEmpty()) {
                    JSONObject quoteObject = jsonArray.getJSONObject(0);
                    String quote = quoteObject.getString("quote");
                    String author = quoteObject.getString("author");
                    HashMap<String, String> objectObjectHashMap = new HashMap<>();
                    objectObjectHashMap.put(author, quote);

                    return objectObjectHashMap;
                } else {
                    System.out.println("No quotes found.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  new HashMap<>();
    }
}
