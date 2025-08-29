package com.example.payments.controller;

import java.util.Map;
import java.util.Optional;

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
            String testValue = Optional.ofNullable(payload.get("key"))
                                 .map(Object::toString)
                                 .orElse(null);

            if (testValue == null) {
                logger.warn("Key 'key' not found or is null in the payload.");
                return ResponseEntity.badRequest().body("Error: 'key' is required and cannot be null.");
            }
            int length = testValue.length();
            return ResponseEntity.ok("Length: " + length);
        } catch (Exception e) {
            logger.error("❌ An unexpected exception occurred in /login", e);
            System.err.println("❌ Unexpected exception stack trace:");
            e.printStackTrace(System.err);
            return ResponseEntity.status(500).body("Error: An unexpected error occurred");
        }
    }
}
