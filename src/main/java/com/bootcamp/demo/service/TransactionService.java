package com.bootcamp.demo.service;

import com.bootcamp.demo.mapper.DocumentToTransactionMapper;
import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

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
}