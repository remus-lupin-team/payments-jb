package com.bootcamp.demo.service;

import com.bootcamp.demo.dao.FirestoreDao;
import com.bootcamp.demo.exception.CardNotFoundException;
import com.bootcamp.demo.exception.FirestoreDaoException;
import com.bootcamp.demo.exception.PaymentFailException;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {
    private final FirestoreDao firestoreDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

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

    public Transaction processPaymentWithPreferredCard(Double amount){
        Transaction transaction = new Transaction();
        String cardId = getCardIdOfPreferredCard();
        try {
            transaction = processPayment(cardId, amount);
        } catch (PaymentFailException | CardNotFoundException e) {
            LOGGER.error("Failed to get preferred card by id " + cardId, e);
        }
        return transaction;
    }

    private String getCardIdOfPreferredCard(){
        return firestoreDao.getPreferredCardId();
    }
}
