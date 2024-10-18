package robsunApi.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.domain.model.EmailRequest;
import robsunApi.service.EmailService;
import robsunApi.service.TokenService;
import robsunApi.util.response.ResponseUtil;

@RestController
public class EmailController {
    private final EmailService emailService;
    private final TokenService tokenService;

    public EmailController(EmailService emailService, TokenService tokenService) {
        this.emailService = emailService;
        this.tokenService = tokenService;
    }
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendEmail(@RequestHeader("x-api-token") String token, @RequestBody EmailRequest emailRequest) {
        if (!tokenService.isValidToken(token)) {
            return ResponseUtil.errorResponse(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        emailService.sendContactForm(emailRequest.getName(), emailRequest.getUserEmail(), emailRequest.getMessageContent());
        return ResponseEntity.ok("Email sent successfully!");
    }
}
