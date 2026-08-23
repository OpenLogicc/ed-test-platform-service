package com.example.demo.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String identifier; // Accepts either email OR mobile number
    private String password;
}
