package com.bootcamp.demo.service;

import com.bootcamp.demo.mapper.DocumentToTransactionMapper;
import com.bootcamp.demo.model.Transaction;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TransactionService {
    private Firestore firestore;
    DocumentToTransactionMapper mapper;

    @Autowired
    public TransactionService(Firestore firestore, DocumentToTransactionMapper mapper) {
        this.firestore = firestore;
        this.mapper = mapper;
    }

    public List<Transaction> getTransactionDetails(){
        this.firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = firestore.collection("transactions").get();
        List<Transaction> transactions = new ArrayList<>();
        try{
            QuerySnapshot querySnapshot= query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for(QueryDocumentSnapshot document : documents){
                Transaction transaction = mapper.mapDocument2Transaction(document);
                transactions.add(transaction);
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return transactions;
    }
}