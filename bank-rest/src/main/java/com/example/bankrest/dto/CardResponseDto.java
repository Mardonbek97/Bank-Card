package com.example.bankrest.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class CardResponseDto {
    private UUID   id;
    private String cardNumber;
    private String ownerName;
    private String expiry;
    private String status;
    private BigDecimal balance;

    public CardResponseDto(UUID id, String cardNumber, String ownerName, String expiry, String status, BigDecimal balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expiry = expiry;
        this.status = status;
        this.balance = balance;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String o) {
        this.ownerName = o;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String e) {
        this.expiry = e;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String s) {
        this.status = s;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal b) {
        this.balance = b;
    }
}
