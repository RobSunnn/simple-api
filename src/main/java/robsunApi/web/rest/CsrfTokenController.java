package robsunApi.web.rest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfTokenController {

    @GetMapping("")
    public CsrfToken csrfToken(CsrfToken csrfToken) {
        return csrfToken;
    }
}
