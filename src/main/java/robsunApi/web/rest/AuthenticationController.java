package robsunApi.web.contoller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import robsunApi.domain.model.binding.UserRegisterBM;
import robsunApi.service.AuthenticationService;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.EMAIL;

@RestController
@RequestMapping("/users")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //    @PreAuthorize("isAnonymous()")
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

    //    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/register", produces = "text/html")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    //    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(UserRegisterBM userRegisterBM) {
        authenticationService.registerUser(userRegisterBM);
        return ResponseEntity.ok("User registered successfully");
    }
}
