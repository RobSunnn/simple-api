package robsunApi.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import robsunApi.domain.model.EmailRequest;
import robsunApi.service.EmailService;
import robsunApi.service.TokenService;

@RestController
@RequestMapping()
public class EmailController {

    private final EmailService emailService;
    private final TokenService tokenService;

    public EmailController(EmailService emailService, TokenService tokenService) {
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    @PostMapping("/sendMail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {

        emailService.sendContactForm(emailRequest.getName(), emailRequest.getUserEmail(), emailRequest.getMessageContent());
        return ResponseEntity.ok("Email sent successfully!");
    }
}
