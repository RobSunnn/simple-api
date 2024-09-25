package robsunApi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${SMTP_USERNAME}")
    private String smtpUsername;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactForm(String name, String userEmail, String messageContent) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(smtpUsername);
        message.setTo(smtpUsername);
        message.setSubject("Contact Form Submission from " + name);
        message.setText("Message from: " + name + "\n" +
                "Email: " + userEmail + "\n\n" +
                "Message:\n" + messageContent);
        message.setReplyTo(userEmail);

        mailSender.send(message);
    }
}
