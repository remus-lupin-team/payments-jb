package com.bootcamp.demo.mapper;

import com.bootcamp.demo.model.Transaction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;


@Component
public class DocumentToTransactionMapper {
    public Transaction mapDocument2Transaction(QueryDocumentSnapshot document) {
        Transaction transaction = new Transaction();
        String id = document.getString("id");
        transaction.setId(id);

        String cardNumber = document.getString("cardNumber");
        transaction.setCardNumber(cardNumber);

        Double amount = document.getDouble("amount");
        transaction.setAmount(amount);

        LocalDateTime timestamp = Objects.requireNonNull(document.getTimestamp("timestamp")).toSqlTimestamp().toLocalDateTime();
        transaction.setTimestamp(timestamp);

        return transaction;
    }
}
