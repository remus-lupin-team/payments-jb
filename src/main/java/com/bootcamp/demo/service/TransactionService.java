package com.bootcamp.demo.service;

import com.bootcamp.demo.mapper.DocumentToTransactionMapper;
import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.repository.TransactionRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private Firestore firestore;
    DocumentToTransactionMapper mapper;
    TransactionRepository repository;

    @Autowired
    public TransactionService(Firestore firestore, DocumentToTransactionMapper mapper, TransactionRepository repository) {
        this.firestore = firestore;
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<Transaction> getAll(){
        return repository.getAll();
    }

    public List<Transaction> filterByAmount(Double minAmount, Double maxAmount){
        return repository.filterByAmount(minAmount, maxAmount);
    }

    public List<Transaction> filterByCardNr(String cardNr){
        return repository.filterByCardNr(cardNr);
    }

    public List<Transaction> filterByDate(LocalDate startDate, LocalDate endDate){
        return repository.filterByDate(startDate, endDate);
    }
}