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
            // This will throw NullPointerException if 'key' is not present or null
            String testValue = (String) payload.get("key");
            if (testValue == null) {
                logger.warn("Received /login request with null 'key' in payload.");
                return ResponseEntity.status(400).body("Error: 'key' cannot be null in the request payload.");
            }
            int length = testValue.length();
            return ResponseEntity.ok("Length: " + length);
        } catch (NullPointerException e) {
            // Log to application logger
            logger.error("❌ NullPointerException occurred in /login", e);

            // Also log raw trace to stderr
            System.err.println("❌ NullPointerException stack trace:");
            e.printStackTrace(System.err);

            return ResponseEntity.status(500).body("Error: Null value encountered");
        } catch (Exception e) {
            logger.error("❌ Unexpected exception occurred in /login", e);
            System.err.println("❌ Unexpected exception stack trace:");
            e.printStackTrace(System.err);

            return ResponseEntity.status(500).body("Error: Unexpected error occurred");
        }
    }
}
