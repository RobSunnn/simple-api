package robsunApi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static robsunApi.service.FactService.getAFact;

@RestController
public class FactController {

    @GetMapping("/fact")
    public String getFact() {
        return getAFact();
    }
}
