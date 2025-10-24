package com.example.bankrest.controller;

import com.example.bankrest.dto.AuthRequest;
import com.example.bankrest.dto.AuthResponse;
import com.example.bankrest.dto.RegisterDto;
import com.example.bankrest.security.JwtUtil;
import com.example.bankrest.service.UserDetailsServiceImpl;
import com.example.bankrest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        UserDetails ud = userDetailsService.loadUserByUsername(req.getUsername());
        String token = jwtUtil.generateToken(ud);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto dto) {
        userService.register(dto);
        return ResponseEntity.status(201).build();
    }
}
