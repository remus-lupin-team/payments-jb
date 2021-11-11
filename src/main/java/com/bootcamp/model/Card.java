package com.bootcamp.model;

public class Card {
    private String holderName;
    private String cardNumber;
    private String CVV;

    private int expirationYear;
    private int expirationMonth;

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
