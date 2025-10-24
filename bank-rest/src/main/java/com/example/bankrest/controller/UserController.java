package com.example.bankrest.controller;

import com.example.bankrest.dto.CardResponseDto;
import com.example.bankrest.service.CardService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final CardService cardService;

    public UserController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public Page<CardResponseDto> myCards(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails) {
        String username = userDetails.getUsername();
        return cardService.listUserCards(username, page, size);
    }

    @PostMapping("/cards")
    public CardResponseDto createCard(@Valid @RequestBody CardResponseDto dto,
                                      @AuthenticationPrincipal UserDetails userDetails) {

        return cardService.createCard(dto, userDetails.getUsername());
    }

    @PatchMapping("/cards/{id}/block")
    public void blockCard(@PathVariable UUID id) {
        cardService.blockCard(id);
    }

}
