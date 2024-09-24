package robsunApi.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class QuoteService {

    public static HashMap<String, String> randomQuote() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = System.getenv("QUOTE_URL");
            String key = System.getenv("KEY");
            String header = System.getenv("HEADER");
            final Logger log = LoggerFactory.getLogger(QuoteService.class);


            HttpGet request = new HttpGet(url);
            request.addHeader(header, key);
            log.info("Fetching a random quote");
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                String responseBody = EntityUtils.toString(response.getEntity());
                JSONArray jsonArray = new JSONArray(responseBody);
                System.out.println(jsonArray);
                if (!jsonArray.isEmpty()) {
                    JSONObject quoteObject = jsonArray.getJSONObject(0);
                    String quote = quoteObject.getString("quote");
                    String author = quoteObject.getString("author");
                    HashMap<String, String> quoteMap = new HashMap<>();
                    quoteMap.put(author, quote);

                    return quoteMap;
                } else {
                    log.warn("No quotes found.");
                }
            }
        } catch (Exception e) {
            final Logger log = LoggerFactory.getLogger(FactService.class);

            log.error(e.getMessage());
        }
        return  new HashMap<>();
    }
}
