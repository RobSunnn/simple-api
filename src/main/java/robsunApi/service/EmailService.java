package robsunApi.service;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    @Value("${SMTP_USERNAME}")
    private String smtpUsername;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendContactForm(String name, String userEmail, String messageContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            String emailContent = generateEmailBody(name, userEmail, messageContent);

            mimeMessageHelper.setFrom(smtpUsername);
            mimeMessageHelper.setTo(smtpUsername);
            mimeMessageHelper.setSubject("Contact Form Submission from " + name);
            mimeMessageHelper.setText(emailContent, true);
            mimeMessageHelper.setReplyTo(userEmail);

            log.info("Sending email from: {}", userEmail);
            mailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private String generateEmailBody(String name, String userEmail, String messageContent) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("email", userEmail);
        context.setVariable("message", messageContent);

        return templateEngine.process("email-template", context);
    }
}


