package com.bootcamp.demo.model;

public class Card {
    private String holderName;
    private String cardNumber;
    private String CVV;
    private CardStateEnum state;
    private Long expirationYear;
    private Long expirationMonth;

    public Card(){}

    public Card(String holderName, String cardNumber, String cvv, Long expirationYear, Long expirationMonth) {
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.CVV = cvv;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public CardStateEnum getState() {
        return state;
    }

    public void setState(CardStateEnum state) {
        this.state = state;
    }

    public Long getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Long expirationYear) {
        this.expirationYear = expirationYear;
    }

    public Long getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Long expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Override
    public String toString() {
        return "Card{" +
                "holderName='" + holderName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvv='" + CVV + '\'' +
                ", state=" + state +
                ", expirationYear=" + expirationYear +
                ", expirationMonth=" + expirationMonth +
                '}';
    }
}