package com.example.demo.Notification;


public class StudentEmailRequest {
    private String studentEmail;
    private String subject;
    private String title;
    private String badgeText;
    private String priceText;
    private String body;
    private String buttonUrl;

    // Getters and Setters
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBadgeText() { return badgeText; }
    public void setBadgeText(String badgeText) { this.badgeText = badgeText; }

    public String getPriceText() { return priceText; }
    public void setPriceText(String priceText) { this.priceText = priceText; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getButtonUrl() { return buttonUrl; }
    public void setButtonUrl(String buttonUrl) { this.buttonUrl = buttonUrl; }
}
