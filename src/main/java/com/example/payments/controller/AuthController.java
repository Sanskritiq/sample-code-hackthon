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
            // Retrieve the value for 'key'
            Object keyValue = payload.get("key");

            // Explicitly check for null
            if (keyValue == null) {
                logger.error("❌ 'key' is missing or null in the payload for /login request.");
                return ResponseEntity.badRequest().body("Error: 'key' is required and cannot be null.");
            }

            // Attempt to cast and use the value
            if (!(keyValue instanceof String)) {
                logger.error("❌ ClassCastException occurred for 'key' in /login: value is not a String. Received type: {}", keyValue.getClass().getName());
                return ResponseEntity.badRequest().body("Error: Value for 'key' must be a string.");
            }

            String testValue = (String) keyValue;
            int length = testValue.length();
            return ResponseEntity.ok("Length: " + length);

        } catch (Exception e) { // Catch any other unexpected exceptions
            logger.error("❌ Unexpected exception occurred in /login", e);
            System.err.println("❌ Unexpected exception stack trace:");
            e.printStackTrace(System.err);
            return ResponseEntity.status(500).body("Error: Unexpected error occurred");
        }
    }
}
