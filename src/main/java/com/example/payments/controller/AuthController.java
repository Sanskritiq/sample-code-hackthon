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

        String testValue = (String) payload.get("key");
        if (testValue != null) {
            int length = testValue.length();
            return ResponseEntity.ok("Length: " + length);
        } else {
            // Provide a more descriptive error for the client
            return ResponseEntity.badRequest().body("Error: 'key' is missing or null in the request payload.");
        }
    }
}
