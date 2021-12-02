package com.bootcamp.demo.dao;

import com.bootcamp.demo.exception.CardNotFoundException;
import com.bootcamp.demo.exception.FirestoreDaoException;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.Transaction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConditionalOnProperty(value = "firebaseKey", havingValue = "false", matchIfMissing = true)
public class FirestoreDaoMock implements FirestoreDao {

    @Override
    public void remove(String id, String collectionName) {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }

    @Override
    public void update(String id, String collectionName, Map updateDetails) {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }

    @Override
    public List<Card> getAll() {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }

    @Override
    public Card addCard(Card card) {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }

    @Override
    public Card getCardById(String cardId) throws FirestoreDaoException, CardNotFoundException {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }

    @Override
    public Card setPreferredCard(String cardNumber) {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }

    @Override
    public List<Transaction> getAllTransactions() {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }

    @Override
    public String getPreferredCardId() {
        throw new UnsupportedOperationException("firebaseKey not provided");
    }
}
