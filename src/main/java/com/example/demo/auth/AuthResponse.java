package com.example.demo.auth;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
    private String fullName;
    private String email;

    public AuthResponse(String token, String message, String fullName, String email) {
        this.token = token;
        this.message = message;
        this.fullName = fullName;
        this.email = email;
    }
}
