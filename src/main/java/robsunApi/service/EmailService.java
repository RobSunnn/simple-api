package robsunApi.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import robsunApi.domain.model.EmailParams;

@Service
public class EmailService {

    public static ResponseEntity<String> sendEmail(EmailParams emailParams) {

        String serviceID = System.getenv("SERVICE_ID");
        String templateID = System.getenv("TEMPLATE_ID");
        String publicKey = System.getenv("PUBLIC_KEY");


        String jsonPayload = String.format("{\"service_id\":\"%s\",\"template_id\":\"%s\",\"user_id\":\"%s\",\"template_params\":{\"name\":\"%s\",\"email\":\"%s\",\"message\":\"%s\"}}",
                serviceID, templateID, publicKey, emailParams.getName(), emailParams.getEmail(), emailParams.getMessage());

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("https://api.emailjs.com/api/v1.0/email/send");

            request.addHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(jsonPayload));

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                if (response.getStatusLine().getStatusCode() == 200) {
                    return ResponseEntity.ok("Email sent successfully: " + responseBody);
                } else {
                    return ResponseEntity.status(response.getStatusLine().getStatusCode()).body("Failed to send email: " + responseBody);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

}
