package com.bootcamp.demo.model;

import lombok.Getter;

@Getter
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
}