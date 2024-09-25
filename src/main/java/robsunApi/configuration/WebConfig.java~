package robsunApi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/weather")  // Allow CORS on the /weather endpoint
                .allowedOrigins("https://robsunnn.github.io/")
                .allowedMethods("POST");  // Allow POST requests

        registry.addMapping("/sendEmail") // Allow CORS on the /sendEmail endpoint
                .allowedOrigins("https://robsunnn.github.io/")
                .allowedMethods("POST");  // Allow POST requests
    }
}