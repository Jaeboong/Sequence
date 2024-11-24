package com.example.Sequence.controller;

import com.example.Sequence.dto.response.ErrorResponse;
import com.example.Sequence.service.UserService;
import com.example.Sequence.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j  // 로깅을 위해 추가
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserValidationController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestHeader("Authorization") String token,
                                          @RequestParam String username) {
        log.debug("Received token: {}", token);
        try {
            // Bearer 토큰 처리 로직 수정
            String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
            log.debug("Processing token: {}", actualToken);

            // 토큰 검증
            Claims claims = jwtUtil.validateAndGetClaims(actualToken);
            String tokenUsername = claims.get("username", String.class);
            log.debug("Token username: {}, Request username: {}", tokenUsername, username);

            // 토큰의 사용자 이름과 전달받은 사용자 이름이 일치하는지 확인
            if (!tokenUsername.equals(username)) {
                log.warn("Username mismatch - Token: {}, Request: {}", tokenUsername, username);
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ErrorResponse("토큰의 사용자와 요청 사용자가 일치하지 않습니다."));
            }

            // 사용자 존재 여부 확인
            boolean exists = userService.existsByUsername(username);
            log.debug("User exists check: {}", exists);

            if (!exists) {
                log.warn("User not found: {}", username);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("존재하지 않는 사용자입니다."));
            }

            return ResponseEntity.ok(new ValidationResponse(true, "유효한 사용자입니다."));

        } catch (Exception e) {
            log.error("Token validation error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("유효하지 않은 토큰입니다."));
        }
    }
}

@Getter
@AllArgsConstructor
class ValidationResponse {
    private boolean valid;
    private String message;
} 