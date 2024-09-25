package robsunApi.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.service.TokenService;

import java.io.Serializable;
import java.util.HashMap;

import static robsunApi.service.FactService.getAFact;
import static robsunApi.service.HobbyGenerator.giveIdeaForHobby;

@RestController
public class HobbyController {

    private final TokenService tokenService;

    public HobbyController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/hobby")
    public ResponseEntity<?> getHobby(@RequestHeader("x-api-token") String token) {
        if (tokenService.isValidToken(token)) {
            // Proceed with request processing if token is valid
            return ResponseEntity.ok(giveIdeaForHobby());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

}
