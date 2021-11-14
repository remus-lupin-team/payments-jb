package com.bootcamp.demo.service;

import com.bootcamp.demo.exception.PaymentFailException;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.Transaction;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    private final Firestore firestore;

    @Autowired
    public PaymentService(Firestore firestore) {
        this.firestore = firestore;
    }

    public Transaction simulatePayment(Card card, Double amount) throws PaymentFailException {
        Random random = new Random();
        if(random.nextInt(10) <= 2){
            throw new PaymentFailException("Transaction failed for card holder: " + card.getHolderName());
        }
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), card.getCardNumber(),amount, LocalDateTime.now());
        firestore.collection("Transactions").add(transaction);
        return transaction;
    }
}
