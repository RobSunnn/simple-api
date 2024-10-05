package robsunApi.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.domain.model.EmailRequest;
import robsunApi.service.EmailService;
import robsunApi.service.TokenService;

@RestController
@RequestMapping()
public class EmailController {

    private final EmailService emailService;


    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {

        emailService.sendContactForm(emailRequest.getName(), emailRequest.getUserEmail(), emailRequest.getMessageContent());
        return ResponseEntity.ok("Email sent successfully!");
    }
}
