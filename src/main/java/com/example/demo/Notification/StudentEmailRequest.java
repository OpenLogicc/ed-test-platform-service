package com.example.demo.Notification;


public class StudentEmailRequest {
    private String studentEmail;
    private String subject;
    private String name;
    private String message;
    private String body;
    private String buttonUrl;

    // Getters and Setters
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getButtonUrl() { return buttonUrl; }
    public void setButtonUrl(String buttonUrl) { this.buttonUrl = buttonUrl; }
}
