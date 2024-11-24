package com.example.Sequence.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private final SecretKey key;
    private final long expiration;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration  // 추가된 매개변수
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;  // 추가된 초기화
    }
    
    public Claims validateAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public boolean validateToken(String token) {
        try {
            validateAndGetClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String extractName(String token) {
        Claims claims = validateAndGetClaims(token);
        return claims.get("username", String.class);
    }

    public String generateToken(String userId, String username) {
    Date now = new Date();
    return Jwts.builder()
            .setSubject(userId)
            .claim("username", username)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + expiration))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }
}