package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.AuthResponse;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.SignupRequest;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use.");
        }
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number is already registered.");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setTargetGoal(request.getTargetGoal());
        user.setTargetExamYear(request.getTargetExamYear());

        userRepository.save(user);

        // Dummy token generation (replace with JWT generation logic)
        String token = "JWT_TOKEN_" + user.getId();

        return new AuthResponse(token, "User registered successfully", user.getFullName(), user.getEmail());
    }

    public AuthResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmailOrMobileNumber(request.getIdentifier())
                .orElseThrow(() -> new RuntimeException("User not found with provided Email/Mobile."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password credentials.");
        }

        // Dummy token generation (replace with JWT generation logic)
        String token = "JWT_TOKEN_" + user.getId();

        return new AuthResponse(token, "Login successful", user.getFullName(), user.getEmail());
    }
}
