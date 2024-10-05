package robsunApi.web.rest;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import robsunApi.domain.model.binding.UserRegisterBM;
import robsunApi.service.AuthenticationService;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.EMAIL;

@RestController
@RequestMapping("/users")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/login", produces = "text/html")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login-error")
    public String onFailure(@ModelAttribute("email") String email,
                            Model model) {

        model.addAttribute(String.valueOf(EMAIL), email);
//        model.addAttribute(BAD_CREDENTIALS, "true");

        return "login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/register", produces = "text/html")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(
            @RequestBody @Valid UserRegisterBM userRegisterBM,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("errors", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(errors);
        }

        authenticationService.registerUser(userRegisterBM);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }
}
