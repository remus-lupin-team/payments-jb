package com.bootcamp.demo.repository;

import com.bootcamp.demo.dao.FirestoreDao;
import com.bootcamp.demo.mapper.DocumentToTransactionMapper;
import com.bootcamp.demo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private final FirestoreDao firestoreDao;
    private final DocumentToTransactionMapper mapper;

    @Autowired
    public TransactionRepository(FirestoreDao firestoreDao, DocumentToTransactionMapper mapper) {
        this.firestoreDao = firestoreDao;
        this.mapper = mapper;

    }

    public List<Transaction> getAll() {
        return firestoreDao.getAllTransactions();
    }

    public List<Transaction> filterByAmount(Double minAmount, Double maxAmount) {
        List<Transaction> list = firestoreDao.getAllTransactions();
        if (maxAmount == null) {
            maxAmount = list.stream()
                    .max(Comparator.comparing(Transaction::getAmount))
                    .get()
                    .getAmount();
        }
        Double finalMaxAmount = maxAmount;
        return list.stream()
                .filter(e -> e.getAmount() >= minAmount && e.getAmount() <= finalMaxAmount)
                .collect(Collectors.toList());
    }

    public List<Transaction> filterByCardNr(String cardNr) {
        List<Transaction> list = firestoreDao.getAllTransactions();
        return list.stream()
                .filter(e -> e.getCardNumber().equals(cardNr))
                .collect(Collectors.toList());
    }

    public List<Transaction> filterByDate(LocalDate startDate, LocalDate endDate) {
        List<Transaction> list = firestoreDao.getAllTransactions();
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        LocalDate finalEndDate = endDate;
        return list.stream()
                .filter(e -> e.getTimestamp().toLocalDate().compareTo(startDate) >= 0 &&
                        e.getTimestamp().toLocalDate().compareTo(finalEndDate) <= 0)
                .collect(Collectors.toList());
    }
}
