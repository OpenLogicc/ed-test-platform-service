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
}
