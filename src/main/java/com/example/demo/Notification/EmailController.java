package com.example.demo.Notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class EmailController {

    private final EmailService emailService;

    // Constructor injection is preferred over field injection
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmailToStudent(@RequestBody StudentEmailRequest request) {
        // Basic payload validation
        if (request.getStudentEmail() == null || request.getStudentEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Error: studentEmail is required.");
        }

        try {
            // Default subject if not provided by frontend
            String subject = (request.getSubject() != null && !request.getSubject().isBlank())
                    ? request.getSubject()
                    : "Welcome to EdTech! Claim your free study pass 🎯";

            // Fallback to name or title if provided, otherwise default to "Future Ranker"
            String studentName = (request.getTitle() != null && !request.getTitle().isBlank())
                    ? request.getTitle()
                    : "Future Ranker";

            String ctaUrl = (request.getButtonUrl() != null && !request.getButtonUrl().isBlank())
                    ? request.getButtonUrl()
                    : "https://yourwebsite.com/dashboard";

            // 1. Build the startup HTML template from resources/templates/education-offer.html
            String htmlTemplate = emailService.buildStartupOfferEmail(studentName, ctaUrl);

            // 2. Dispatch the HTML email
            emailService.sendHtmlEmail(
                    request.getStudentEmail().trim(),
                    subject,
                    htmlTemplate
            );

            return ResponseEntity.ok("Email sent successfully to " + request.getStudentEmail());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}
