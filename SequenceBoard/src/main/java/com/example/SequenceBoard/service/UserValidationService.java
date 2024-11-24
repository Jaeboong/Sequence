package com.example.SequenceBoard.service;

import com.example.SequenceBoard.client.AuthServiceClient;
import com.example.SequenceBoard.dto.ValidationResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {
    
    private final AuthServiceClient authServiceClient;
    
    @Cacheable(value = "userValidation", key = "#username")
    @CircuitBreaker(name = "validateUser", fallbackMethod = "validateUserFallback")
    public boolean validateUser(String token, String username) {
        try {
            log.debug("Validating user: {} with token: {}", username, token);
            
            ResponseEntity<ValidationResponse> response = 
                authServiceClient.validateUser(token, username);
            
            if (response.getBody() == null) {
                log.warn("Validation response body is null for user: {}", username);
                return false;
            }
            
            boolean isValid = response.getBody().isValid();
            log.debug("Validation result for user {}: {}", username, isValid);
            return isValid;
            
        } catch (Exception e) {
            log.error("Validation error for user {}: {}", username, e.getMessage());
            return false;
        }
    }
    
    private boolean validateUserFallback(String token, String username, Exception e) {
        log.warn("Circuit breaker fallback for user {}: {}", username, e.getMessage());
        return false;
    }
} 