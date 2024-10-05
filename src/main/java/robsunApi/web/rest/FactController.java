package robsunApi.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static robsunApi.service.FactService.getAFact;

@RestController
public class FactController {

    @GetMapping("/fact")
    public ResponseEntity<?> getFact() {
        return ResponseEntity.ok(getAFact());
    }
}
