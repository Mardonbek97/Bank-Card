package com.example.bankrest.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class UserStatusChangeDto {
    private UUID id;
    @NotBlank
    private String username;
    private String status;

    public UserStatusChangeDto(UUID id, String username, String status) {
        this.id=id;
        this.username=username;
        this.status=status;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
