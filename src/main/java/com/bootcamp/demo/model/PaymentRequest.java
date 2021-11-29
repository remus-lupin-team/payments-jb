package com.bootcamp.demo.model;

import java.util.Objects;

public class PaymentRequest {
    private String cardId;
    private double amount;

    public PaymentRequest() {
    }

    public PaymentRequest(String cardId, double amount) {
        this.cardId = cardId;
        this.amount = amount;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentRequest)) return false;
        PaymentRequest that = (PaymentRequest) o;
        return Double.compare(that.getAmount(), getAmount()) == 0 && Objects.equals(getCardId(), that.getCardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getAmount());
    }
}
