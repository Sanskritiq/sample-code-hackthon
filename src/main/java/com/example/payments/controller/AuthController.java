package com.example.payments.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<String> nullPointerTest(@RequestBody Map<String, Object> payload) {
        logger.info("Received /login request with payload: {}", payload);

        try {
            String testValue = (String) payload.get("key");

            // Add null check for testValue
            if (testValue == null) {
                logger.error("❌ 'key' field is missing or null in the request payload.");
                return ResponseEntity.badRequest().body("Error: 'key' field is required and cannot be null.");
            }

            int length = testValue.length();
            return ResponseEntity.ok("Length: " + length);
        } catch (ClassCastException e) {
            // Handle case where 'key' exists but is not a String
            logger.error("❌ 'key' field is not a String in the request payload.", e);
            return ResponseEntity.badRequest().body("Error: 'key' field must be a string.");
        } catch (Exception e) {
            logger.error("❌ Unexpected exception occurred in /login", e);
            System.err.println("❌ Unexpected exception stack trace:");
            e.printStackTrace(System.err);

            return ResponseEntity.status(500).body("Error: Unexpected error occurred");
        }
    }
}