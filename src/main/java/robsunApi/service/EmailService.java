package robsunApi.service;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import robsunApi.domain.event.ContactRequestEvent;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Value("${SMTP_USERNAME}")
    private String smtpUsername;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine, ApplicationEventPublisher applicationEventPublisher) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.applicationEventPublisher = applicationEventPublisher;
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

            applicationEventPublisher.publishEvent(
                    new ContactRequestEvent("ContactRequest", userEmail, name)
            );

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void sendContactFormRespond(String userEmail, String name) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            String emailContent = generateEmailBody(name);

            mimeMessageHelper.setFrom(smtpUsername);
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject("Thank you for your message, " + name + "!");
            mimeMessageHelper.setText(emailContent, true);
            mimeMessageHelper.setReplyTo(smtpUsername);

            log.info("Sending email respond to: {}", userEmail);
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

    private String generateEmailBody(String name) {
        Context context = new Context();
        context.setVariable("name", name);

        return templateEngine.process("contact-request-respond", context);
    }

    @EventListener(ContactRequestEvent.class)
    private void onContactRequestEvent(ContactRequestEvent event) {
        sendContactFormRespond(event.getEmail(), event.getName());
    }
}


