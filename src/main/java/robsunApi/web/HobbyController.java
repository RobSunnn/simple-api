package robsunApi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static robsunApi.service.HobbyGenerator.giveIdeaForHobby;

@RestController
public class HobbyController {

    @GetMapping("/hobby")
    public HashMap<String, String> getHobby() {
        return giveIdeaForHobby();
    }

}
