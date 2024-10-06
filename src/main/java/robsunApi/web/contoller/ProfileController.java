package robsunApi.web.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import robsunApi.domain.model.view.UserView;
import robsunApi.service.AuthenticationService;

@Controller
@RequestMapping("/users/profile")
public class ProfileController {

    private final AuthenticationService authenticationService;

    public ProfileController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String profile(Model model) {
        UserView userDetails = authenticationService.findUserDetails();
        model.addAttribute("userDetails", userDetails);
        return "profile";
    }
}
