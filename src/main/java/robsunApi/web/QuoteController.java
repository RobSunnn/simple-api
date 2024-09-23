package robsunApi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static robsunApi.service.QuoteService.randomQuote;

@RestController
public class QuoteController {

    @GetMapping("/quote")
    public Map<String, String> getQuote() {
        return randomQuote();
    }

}
