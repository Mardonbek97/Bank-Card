package com.example.bankrest.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debit_id")
    private Card debitCard;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    private Card creditCard;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    private BigDecimal amount;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Card getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(Card debitCard) {
        this.debitCard = debitCard;
    }

    public Card getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Card creditCard) {
        this.creditCard = creditCard;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
