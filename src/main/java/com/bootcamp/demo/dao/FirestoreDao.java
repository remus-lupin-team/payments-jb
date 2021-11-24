package com.bootcamp.demo.dao;

import com.bootcamp.demo.model.Card;

import java.util.List;

public interface FirestoreDao {
    void remove(String id, String collectionName);

    List<Card> getAll();

    void addCard(Card card);

    Card setPreferredCard(String cardNumber);
}
