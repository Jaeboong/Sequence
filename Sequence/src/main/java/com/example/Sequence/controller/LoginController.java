package com.example.Sequence.controller;

import com.example.Sequence.entity.User;
import com.example.Sequence.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.login(loginRequest.getId(), loginRequest.getPassword());
            
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(new LoginResponse(user.getId(), "로그인에 성공했습니다."));
            } else {
                return ResponseEntity.badRequest()
                    .body(new ErrorResponse("비밀번호가 일치하지 않습니다."));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse(e.getMessage()));
        }
    }
}

@Getter
@AllArgsConstructor
class LoginRequest {
    private String id;
    private String password;
}

@Getter
@AllArgsConstructor
class LoginResponse {
    private String id;
    private String message;
}

@Getter
@AllArgsConstructor
class ErrorResponse {
    private String message;
} 