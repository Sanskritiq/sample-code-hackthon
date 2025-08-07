package com.example.payments.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus; // Import HttpStatus
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

            if (testValue == null) {
                logger.warn("The 'key' field in the payload is missing or null for /api/login request.");
                return ResponseEntity.badRequest().body("Error: 'key' field is missing or null in the request body.");
            }

            int length = testValue.length();
            return ResponseEntity.ok("Length: " + length);
        } catch (ClassCastException e) {
            logger.error("❌ ClassCastException occurred in /login: 'key' value is not a String", e);
            System.err.println("❌ ClassCastException stack trace:");
            e.printStackTrace(System.err);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: 'key' value must be a string.");
        } catch (Exception e) {
            logger.error("❌ Unexpected exception occurred in /login", e);
            System.err.println("❌ Unexpected exception stack trace:");
            e.printStackTrace(System.err);
            return ResponseEntity.status(500).body("Error: Unexpected error occurred");
        }
    }
}