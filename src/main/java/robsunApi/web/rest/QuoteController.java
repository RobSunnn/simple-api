package robsunApi.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static robsunApi.service.QuoteService.randomQuote;

@RestController
public class QuoteController {

    @GetMapping("/quote")
    public ResponseEntity<?> getQuote() {
        return ResponseEntity.ok(randomQuote());
    }

}
