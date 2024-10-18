package robsunApi.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.service.TokenService;
import robsunApi.util.response.ResponseUtil;

import static robsunApi.service.QuoteService.randomQuote;

@RestController
public class QuoteController {
    private final TokenService tokenService;

    public QuoteController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @GetMapping("/quote")
    public ResponseEntity<?> getQuote(@RequestHeader("x-api-token") String token) {
        if (!tokenService.isValidToken(token)) {
            return ResponseUtil.errorResponse(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        return ResponseEntity.ok(randomQuote());
    }

}
