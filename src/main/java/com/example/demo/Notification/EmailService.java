package com.example.demo.Notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendHtmlEmail(String toEmail, String subject, String htmlContent) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

        helper.setFrom(senderEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }

    public String buildStartupOfferEmail(String studentName, String ctaUrl) {
        String safeName = (studentName != null && !studentName.isBlank()) ? studentName : "Future Ranker";
        String safeUrl = (ctaUrl != null && !ctaUrl.isBlank()) ? ctaUrl : "https://yourwebsite.com";

        try {
            ClassPathResource resource = new ClassPathResource("templates/education-offer.html");
            String htmlTemplate = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            return htmlTemplate
                    .replace("{{STUDENT_NAME}}", safeName)
                    .replace("{{CTA_URL}}", safeUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load email template file", e);
        }
    }
}
