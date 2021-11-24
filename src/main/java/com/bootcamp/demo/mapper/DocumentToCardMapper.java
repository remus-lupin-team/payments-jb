package com.bootcamp.demo.mapper;

import com.bootcamp.demo.model.Card;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.stereotype.Component;

@Component
public class DocumentToCardMapper {
    public Card mapDocument2Card(QueryDocumentSnapshot document){
        Card card = new Card();
        String holderName = document.getString("holderName");
        card.setHolderName(holderName);

        String cardNumber = document.getString("cardNumber");
        card.setCardNumber(cardNumber);

        String CVV = document.getString("CVV");
        card.setCVV(CVV);

        Long expirationYear = document.getLong("expirationYear");
        card.setExpirationYear(expirationYear);

        Long expirationMonth = document.getLong("expirationMonth");
        card.setExpirationMonth(expirationMonth);

        return card;
    }
}