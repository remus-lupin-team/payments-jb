package com.bootcamp.demo.service;

import com.bootcamp.demo.dao.FirestoreDao;
import com.bootcamp.demo.exception.CardNotFoundException;
import com.bootcamp.demo.exception.FirestoreDaoException;
import com.bootcamp.demo.exception.PaymentFailException;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    private final FirestoreDao firestoreDao;

    @Autowired
    public PaymentService(FirestoreDao firestoreDao) {
        this.firestoreDao = firestoreDao;
    }

    public Transaction processPayment(String cardId, Double amount) throws PaymentFailException, CardNotFoundException {
        Card card;
        try {
            card = firestoreDao.getCardById(cardId);
        } catch (FirestoreDaoException e) {
            throw new PaymentFailException("Failed to get card by id " + cardId, e);
        }
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), card.getCardNumber(), amount, LocalDateTime.now());
        return firestoreDao.addTransaction(transaction);
    }
}
