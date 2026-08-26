package com.example.demo.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String identifier; // Accepts either email OR mobile number
    private String password;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
