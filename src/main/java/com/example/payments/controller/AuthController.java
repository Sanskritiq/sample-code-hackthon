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

    // ❌ Controller to trigger a NullPointerException
    @PostMapping("/login")
    public ResponseEntity<String> nullPointerTest(@RequestBody Map<String, Object> payload) {
        logger.info("Received null pointer test request with payload: {}", payload);

        try {
            String testValue = (String) payload.get("key");
            int length = testValue.length();  // This line may throw NullPointerException
            return ResponseEntity.ok("Length: " + length);
        } catch (NullPointerException e) {
            logger.error("NullPointerException occurred", e);
            System.err.println("NullPointerException occurred", e);

            return ResponseEntity.status(500).body("Error: Null value encountered");
        }
    }
}