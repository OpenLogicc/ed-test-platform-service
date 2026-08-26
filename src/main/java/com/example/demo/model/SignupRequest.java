package com.example.demo.model;

import lombok.Data;

@Data
public class SignupRequest {
    private String fullName;
    private String email;
    private String mobileNumber;
    private String password;
    private String targetGoal;
    private String targetExamYear;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTargetGoal() {
        return targetGoal;
    }

    public void setTargetGoal(String targetGoal) {
        this.targetGoal = targetGoal;
    }

    public String getTargetExamYear() {
        return targetExamYear;
    }

    public void setTargetExamYear(String targetExamYear) {
        this.targetExamYear = targetExamYear;
    }
}
