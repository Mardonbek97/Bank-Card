package com.example.bankrest.controller;

import com.example.bankrest.dto.CardResponseDto;
import com.example.bankrest.dto.UserStatusChangeDto;
import com.example.bankrest.repository.CardRepository;
import com.example.bankrest.repository.UserRepository;
import com.example.bankrest.service.CardService;
import com.example.bankrest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final CardService cardService;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public AdminController(CardService cardService, CardRepository cardRepository, UserRepository userRepository, UserService userService) {
        this.cardService = cardService;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/allCards")
    public Page<CardResponseDto> allCards(Pageable pageable) {
        return cardService.getAllCards(pageable);
    }

    @PostMapping("/cards")
    public CardResponseDto createCard(@Valid @RequestBody CardResponseDto dto) {
        return cardService.createCard(dto, "admin");
    }

    @PatchMapping("/cards/{id}/block")
    public void blockCard(@PathVariable UUID id) {
        cardService.blockCard(id);
    }

    @PatchMapping("/cards/{id}/activate")
    public void activateCard(@PathVariable UUID id) {
        cardService.activateCard(id);
    }

    @DeleteMapping("/cards/{id}")
    public void deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> changeUserStatus(
            @PathVariable UUID id,
            @RequestParam String active) {

        userService.changeUserStatus(id, active);
        return ResponseEntity.ok("Succesfully updated");

    }

    @GetMapping("/listUsers")
    public List<UserStatusChangeDto> allUsers() {
        return userService.getAllUsers();
    }
}
