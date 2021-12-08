package com.bootcamp.demo.dao;

import java.util.Map;

import com.bootcamp.demo.exception.CardNotFoundException;
import com.bootcamp.demo.exception.FirestoreDaoException;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.Transaction;

import java.util.List;

public interface FirestoreDao {
    void removeCard(String cardNumber);

    void updateCard(String cardNumber, Map updateDetails);

    List<Card> getAll();

    Card addCard(Card card);

    Card getCardByCardNumber(String cardNumber) throws FirestoreDaoException, CardNotFoundException;

    Card setPreferredCard(String cardNumber);

    List<Transaction> getAllTransactions();

    Transaction addTransaction(Transaction transaction);

    String getPreferredCardNumber();
}
