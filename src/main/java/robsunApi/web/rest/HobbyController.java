package robsunApi.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.service.TokenService;
import robsunApi.util.response.ResponseUtil;

import static robsunApi.service.HobbyGenerator.giveIdeaForHobby;

@RestController
public class HobbyController {
    private final TokenService tokenService;

    public HobbyController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/hobby")
    public ResponseEntity<?> getHobby(@RequestHeader("x-api-token") String token) {
        if (!tokenService.isValidToken(token)) {
            return ResponseUtil.errorResponse(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        return ResponseEntity.ok(giveIdeaForHobby());
    }

}
