package com.example.bankrest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransferRequest {

    @NotNull
    private String debitCard;

    @NotNull
    private String creditCard;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    public String getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(String debitCardId) {
        this.debitCard = debitCardId;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
