package com.bootcamp.demo.service;

import com.bootcamp.demo.dao.FirestoreDao;
import com.bootcamp.demo.exception.PaymentFailException;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    private final FirestoreDao firestore;

    @Autowired
    public PaymentService(FirestoreDao firestore) {
        this.firestore = firestore;
    }

    public Transaction simulatePayment(Card card, Double amount) throws PaymentFailException {
        Random random = new Random();
        if(random.nextInt(10) <= 2){
            throw new PaymentFailException("Transaction failed for card holder: " + card.getHolderName());
        }
        if(amount <= 0){
            throw new PaymentFailException("Amount less than or equal to 0 for card holder: " + card.getHolderName());
        }
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), card.getCardNumber(), amount, LocalDateTime.now());
        return firestore.addTransaction(transaction);
    }
}
