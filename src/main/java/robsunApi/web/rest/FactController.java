package robsunApi.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.service.TokenService;
import robsunApi.util.response.ResponseUtil;

import static robsunApi.service.FactService.getAFact;

@RestController
public class FactController {

    private final TokenService tokenService;

    public FactController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/fact")
    public ResponseEntity<?> getFact(@RequestHeader("x-api-token") String token) {
        if (!tokenService.isValidToken(token)) {
            return ResponseUtil.errorResponse(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        return ResponseEntity.ok(getAFact());
    }
}
