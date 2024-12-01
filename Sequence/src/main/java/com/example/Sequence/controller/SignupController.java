package com.example.Sequence.controller;

import com.example.Sequence.dto.UserSignupRequest;
import com.example.Sequence.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;

    // 회원가입 요청 처리
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) {
        try {
            String result = signupService.signup(request);
            return ResponseEntity.ok(result); // 성공 응답
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 중복 또는 유효성 에러 처리
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error"); // 서버 에러 처리
        }
    }
}
