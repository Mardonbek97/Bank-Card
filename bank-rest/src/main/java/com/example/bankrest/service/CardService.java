package com.example.bankrest.service;

import com.example.bankrest.dto.CardResponseDto;
import com.example.bankrest.entity.Card;
import com.example.bankrest.entity.User;
import com.example.bankrest.exception.ApiException;
import com.example.bankrest.repository.CardRepository;
import com.example.bankrest.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public Page<CardResponseDto> getAllCards(Pageable pageable) {
        return cardRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Transactional
    public CardResponseDto createCard(CardResponseDto dto, String creatorUsername) {

        if (!dto.getOwnerName().equals(creatorUsername) && creatorUsername != "admin") {
            throw new ApiException("Вы не можете добавить карту для другого пользователя!!!");
        }

        User owner = userRepository.findByUsername(creatorUsername).orElseThrow(() -> new ApiException("User not found"));
        //admin создать card
        User cardHolder = userRepository.findByUsername(dto.getOwnerName()).orElseThrow(() -> new ApiException("User not found"));

        Card c = new Card();
        c.setCardNumber(dto.getCardNumber());
        c.setOwnerName(dto.getOwnerName());
        c.setExpiry(dto.getExpiry());
        c.setStatus("ACTIVE");
        c.setBalance(dto.getBalance());
        c.setUser(owner);
        Card saved = cardRepository.save(c);
        return mapToResponse(saved);
    }

    @Transactional
    public void blockCard(UUID id) {
        Card c = cardRepository.findById(id).orElseThrow(() -> new ApiException("Card not found"));
        c.setStatus("BLOCKED");
        cardRepository.save(c);
    }

    @Transactional
    public void activateCard(UUID cardId) {
        Card c = cardRepository.findById(cardId).orElseThrow(() -> new ApiException("Card not found"));
        c.setStatus("ACTIVE");
        cardRepository.save(c);
    }

    @Transactional
    public void expiredCard(UUID cardId) {
        Card c = cardRepository.findById(cardId).orElseThrow(() -> new ApiException("Card not found"));
        c.setStatus("EXPIRED");
        cardRepository.save(c);
    }

    @Transactional
    public void deleteCard(UUID cardId) {
        Card c = cardRepository.findById(cardId).orElseThrow(() -> new ApiException("Card not found"));
        c.setStatus("DELETED");
        cardRepository.save(c);
    }


    public CardResponseDto mapToResponse(Card c) {
        Card card = new Card();
        CardResponseDto r = new CardResponseDto(card.getId(), card.getCardNumber(), card.getOwnerName(), card.getExpiry(), card.getStatus(), card.getBalance());
        r.setCardNumber(c.getCardNumber());
        r.setOwnerName(c.getOwnerName());
        r.setExpiry(c.getExpiry());
        r.setStatus(c.getStatus());
        r.setBalance(c.getBalance());
        return r;
        /*CardResponseDto dto = new CardResponseDto(
                c.getId(),
                c.getCardNumber(),
                c.getOwnerName(),
                c.getExpiry(),
                c.getStatus(),
                c.getBalance()
        );
        return dto;*/

    }


    public Page<CardResponseDto> listUserCards(String username, int page, int size) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Card> cards = cardRepository.findByUser(user, pageable);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        return cards.map(card -> new CardResponseDto(
                card.getId(),
                maskCardNumber(card.getCardNumber()),
                card.getOwnerName(),
                card.getExpiry(),
                card.getStatus(),
                card.getBalance()
        ));

    }

    private String maskCardNumber(String number) {
        if (number == null || number.length() < 4) return "****";
        String last4 = number.substring(number.length() - 4);
        return "**** **** **** " + last4;
    }

}
