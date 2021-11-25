package com.bootcamp.demo.service;

import com.bootcamp.demo.dao.FirestoreDao;
import com.bootcamp.demo.model.Card;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import java.util.List;


@Service
public class CardService {
    private final FirestoreDao firestoreDao;

    public CardService(FirestoreDao firestoreDao) {
        this.firestoreDao = firestoreDao;
    }
    public void removeCard(String id){
        this.firestoreDao.remove(id, "cards");
    }

    public List<Card> getAll() {
        return firestoreDao.getAll();
    }

    public void updateCard(String id, Card cardDetails){
        Map updateDetails = new HashMap();
        updateDetails.put("cardNumber", cardDetails.getCardNumber());
        updateDetails.put("holderName", cardDetails.getHolderName());
        updateDetails.put("cvv", cardDetails.getCVV());
        updateDetails.put("expirationYear", cardDetails.getExpirationYear());
        updateDetails.put("expirationMonth", cardDetails.getExpirationMonth());
        this.firestoreDao.update(id,"cards", updateDetails);
    }

    public Card setPreferredCard(String cardNumber) {
        return firestoreDao.setPreferredCard(cardNumber);
    }

    public Card addCard(Card card) {
       return firestoreDao.addCard(card);
    }
}
