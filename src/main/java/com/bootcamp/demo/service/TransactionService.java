package com.bootcamp.demo.service;

import com.bootcamp.demo.mapper.DocumentToTransactionMapper;
import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("$#.##");
    private final DocumentToTransactionMapper mapper;
    private final TransactionRepository repository;

    @Autowired
    public TransactionService(DocumentToTransactionMapper mapper, TransactionRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<Transaction> getAll() {
        return repository.getAll();
    }

    public List<Transaction> filterByAmount(Double minAmount, Double maxAmount) {
        return repository.filterByAmount(minAmount, maxAmount);
    }

    public List<Transaction> filterByCardNr(String cardNr) {
        return repository.filterByCardNr(cardNr);
    }

    public List<Transaction> filterByDate(LocalDate startDate, LocalDate endDate) {
        return repository.filterByDate(startDate, endDate);
    }

    public List<Transaction> getTransactionDetails() {
        return repository.getAll();
    }

    public double getAccountStatement(LocalDate startDate, LocalDate endDate){
        List<Transaction> transactions = repository.filterByDate(startDate, endDate);
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public String getFormattedAccountStatement(LocalDate startDate, LocalDate endDate){
        return DECIMAL_FORMAT.format(getAccountStatement(startDate, endDate));
    }
}