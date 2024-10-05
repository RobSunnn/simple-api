package robsunApi.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.service.TokenService;

import static robsunApi.service.QuoteService.randomQuote;

@RestController
public class QuoteController {

    private final TokenService tokenService;

    public QuoteController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @GetMapping("/quote")
    public ResponseEntity<?> getQuote() {
        return ResponseEntity.ok(randomQuote());
    }

}
