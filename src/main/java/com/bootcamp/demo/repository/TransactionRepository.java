package com.bootcamp.demo.repository;

import com.bootcamp.demo.dao.FirestoreDao;
import com.bootcamp.demo.mapper.DocumentToTransactionMapper;
import com.bootcamp.demo.model.Transaction;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    List<Transaction> list;
    //    private Firestore firestore;
    DocumentToTransactionMapper mapper;
    private final FirestoreDao firestoreDao;

    public TransactionRepository(List<Transaction> list, FirestoreDao firestoreDao, DocumentToTransactionMapper mapper) {
        this.list = list;
        this.firestoreDao = firestoreDao;
        this.mapper = mapper;

    }

    @Autowired
    public TransactionRepository(DocumentToTransactionMapper mapper, FirestoreDao firestoreDao) {
        this.mapper = mapper;
        this.firestoreDao = firestoreDao;
        list = new ArrayList<Transaction>();
        getFromDB();
    }

    public List<Transaction> getAll() {
        return list;
    }

    public void getFromDB() {
        list = firestoreDao.getAllTransactions();
//        firestore = FirestoreClient.getFirestore();
//        ApiFuture<QuerySnapshot> query = firestore.collection("transactions").get();
//        List<Transaction> transactions = new ArrayList<>();
//        try{
//            QuerySnapshot querySnapshot= query.get();
//            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
//            for(QueryDocumentSnapshot document : documents){
//                Transaction transaction = mapper.mapDocument2Transaction(document);
//                transactions.add(transaction);
//            }
//        }catch (InterruptedException | ExecutionException e){
//            e.printStackTrace();
//        }
//        list = transactions;
    }

    public List<Transaction> filterByAmount(Double minAmount, Double maxAmount){
//        List<Transaction> list = repository.getAll();
        if(maxAmount == null){
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

    public List<Transaction> filterByCardNr(String cardNr){
//        List<Transaction> list = repository.getAll();
        return list.stream()
                .filter(e -> e.getCardNumber().equals(cardNr))
                .collect(Collectors.toList());
    }

    public List<Transaction> filterByDate(LocalDate startDate, LocalDate endDate){
//        List<Transaction> list = repository.getAll();
        if(endDate == null){
            endDate = LocalDate.now();
        }
        LocalDate finalEndDate = endDate;
        return list.stream()
                .filter(e -> e.getTimestamp().toLocalDate().compareTo(startDate) >= 0 &&
                        e.getTimestamp().toLocalDate().compareTo(finalEndDate) <= 0)
                .collect(Collectors.toList());
    }
}
