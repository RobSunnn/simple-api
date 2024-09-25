package robsunApi.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.service.TokenService;

import java.util.Map;

import static robsunApi.service.HobbyGenerator.giveIdeaForHobby;
import static robsunApi.service.QuoteService.randomQuote;

@RestController
public class QuoteController {

    private final TokenService tokenService;

    public QuoteController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @GetMapping("/quote")
    public ResponseEntity<?> getQuote(@RequestHeader("x-api-token") String token) {
        if (tokenService.isValidToken(token)) {
            // Proceed with request processing if token is valid
            return ResponseEntity.ok(randomQuote());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

}
