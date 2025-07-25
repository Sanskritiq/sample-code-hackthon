package com.example.payments.service;

import com.example.payments.model.UserProfile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserProfileService {

    public UserProfile getUserProfile(String userId) {
        // In a real application, this would retrieve data from a database
        // For demonstration purposes, return a dummy user profile
        if ("123".equals(userId)) {
            return new UserProfile(
                "123",
                "john.doe",
                "john.doe@example.com",
                "John",
                "Doe",
                LocalDate.of(1990, 5, 15),
                "123 Main St, Anytown, USA",
                "555-123-4567"
            );
        } else {
            return null; // User not found
        }
    }
}
