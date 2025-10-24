package com.example.bankrest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256).compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date exp = Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody().getExpiration();
        return exp.before(new Date());
    }
}
