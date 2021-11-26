package com.bootcamp.demo.controller;


import com.bootcamp.demo.model.Card;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class CardValidation {

    public Boolean isValidCard(Card card){
        return isValidCardNumber(card.getCardNumber()) && isValidCVV(card.getCVV()) &&
                isValidExpiration(card.getExpirationYear(), card.getExpirationMonth()) && isValidHolderName(card.getHolderName());
    }

    private Boolean isValidExpiration(long year, long month){
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
}
