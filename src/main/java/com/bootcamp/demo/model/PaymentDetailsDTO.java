package com.bootcamp.demo.model;

public class PaymentDetailsDTO {
    private Card card;
    private double amount;

    public PaymentDetailsDTO() {
    }

    public PaymentDetailsDTO(Card card, double amount) {
        this.card = card;
        this.amount = amount;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
