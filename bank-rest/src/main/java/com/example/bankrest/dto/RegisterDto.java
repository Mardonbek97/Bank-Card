package com.example.bankrest.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class RegisterDto {
    private UUID id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        this.username = u;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        this.password = p;
    }
}
