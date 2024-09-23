package robsunApi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static robsunApi.service.FactService.getAFact;

@RestController
public class FactController {

    @GetMapping("/fact")
    public HashMap<String, String> getFact() {
        return getAFact();
    }
}
