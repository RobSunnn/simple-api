package robsunApi.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.domain.model.EmailParams;

import static robsunApi.service.EmailService.sendEmail;

@RestController
public class EmailController {

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendMail(@RequestBody EmailParams emailParams) {
        return sendEmail(emailParams);
    }
}
