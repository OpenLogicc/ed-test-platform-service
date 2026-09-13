package com.example.demo.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmailToStudent(@RequestBody StudentEmailRequest request) {
        try {
            emailService.sendSimpleEmail(
                    request.getStudentEmail(),
                    request.getSubject(),
                    request.getBody()
            );
            return ResponseEntity.ok("Email sent successfully to " + request.getStudentEmail());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to send email: " + e.getMessage());
        }
    }
}
