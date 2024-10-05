package robsunApi.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static robsunApi.service.HobbyGenerator.giveIdeaForHobby;

@RestController
public class HobbyController {

    @GetMapping("/hobby")
    public ResponseEntity<?> getHobby() {
        return ResponseEntity.ok(giveIdeaForHobby());
    }

}
