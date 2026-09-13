package com.example.demo.Notification;

public class StudentEmailRequest {
    private String studentEmail;
    private String subject;
    private String body;

    // Getters and Setters
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
