package com.bootcamp.demo.model;

public class Card {
    private String holderName;
    private String cardNumber;
    private String CVV;
    private int expirationYear;
    private int expirationMonth;

    public Card(String holderName, String cardNumber, String cvv, int expirationYear, int expirationMonth) {
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        CVV = cvv;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }
}