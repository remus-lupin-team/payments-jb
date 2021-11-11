package com.bootcamp.model;

import java.util.Calendar;

public class Card {
    private String holderName;
    private String cardNumber;
    private String CVV;

    private int expirationYear;
    private int expirationMonth;

    public Card(String holderName, String cardNumber, String CVV, int expirationYear, int expirationMonth) throws Exception {
        if (isValidCardNumber(cardNumber) && isValidCVV(CVV) &&
                isValidExpiration(expirationYear, expirationMonth) && isValidHolderName(holderName)){
            this.holderName = holderName;
            this.cardNumber = cardNumber;
            this.CVV = CVV;
            this.expirationYear = expirationYear;
            this.expirationMonth = expirationMonth;
        } else {
            throw new Exception("Incorrect card fields. Please try again.");
        }
    }

    private Boolean isValidExpiration(int year, int month){
        int currentYear =  Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        if ((year < currentYear) || (month > 13) || (month < 0))
            return false;

        if (year == currentYear && month < currentMonth)
            return false;

        return true;
    }

    private Boolean isValidCardNumber(String givenNumber){
        if (givenNumber.length()!=16){
            return false;
        }

        for (char c: givenNumber.toCharArray()){
            if (!Character.isDigit(c))
                return false;
        }

        return true;
    }

    private Boolean isValidHolderName(String givenText){
        if (givenText.length() < 5)
            return false;

        for (char c: givenText.toCharArray()){
            if (Character.isDigit(c))
                return false;
        }

        return true;
    }

    private Boolean isValidCVV(String givenNumber){
        if (givenNumber.length()!=3 && givenNumber.length()!=4){
            return false;
        }

        for (char c: givenNumber.toCharArray()){
            if (!Character.isDigit(c))
                return false;
        }

        return true;
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
