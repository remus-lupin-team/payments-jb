package com.bootcamp.demo.dao;

import java.util.Map;

import com.bootcamp.demo.model.Card;

import java.util.List;

public interface FirestoreDao {
    void remove(String id, String collectionName);
    void update(String id, String collectionName, Map updateDetails);

    List<Card> getAll();

    Card addCard(Card card);

    Card setPreferredCard(String cardNumber);
}
