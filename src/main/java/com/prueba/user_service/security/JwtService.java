package com.prueba.user_service.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    @Value("${security.secretkey}")
    private String secretKey;

    @Value("${security.token-expiration}")
    private int tokenExpiration;

    private Key getSigningKey() {
       return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) // 1 día
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
}
