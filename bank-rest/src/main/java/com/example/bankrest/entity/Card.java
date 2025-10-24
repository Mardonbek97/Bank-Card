package com.example.bankrest.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;
    @Column(name = "card_number", nullable = false, unique = true, length = 512)
    private String cardNumber;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "expiry")
    private String expiry;
    @Column(name = "status")
    private String status;
    @Column(name = "balance", precision = 19, scale = 2)
    private BigDecimal balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @UpdateTimestamp
    private LocalDateTime updateAt ;

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

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.math.BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(java.math.BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
