package com.bootcamp.demo.mapper;

import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.model.TransactionDTO;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

@Component
public class TransactionDtoMapper {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("$#.##");

    public TransactionDTO transactionToTransactionDto(Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        String cardNumberMask = "**** **** **** ";
        transactionDTO.setCardNumber(cardNumberMask + transaction.getCardNumber().substring(12));
        transactionDTO.setTimestamp(transaction.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss")));
        transactionDTO.setAmount(DECIMAL_FORMAT.format(transaction.getAmount()));
        return transactionDTO;
    }
}
