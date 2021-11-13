package com.bootcamp.demo.service;

import com.bootcamp.demo.mapper.DocumentToCardMapper;
import com.bootcamp.demo.model.Card;
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
public class CardService {

    private Firestore firestoreDB;
    DocumentToCardMapper mapper;

    @Autowired
    public CardService(DocumentToCardMapper mapper) {
        this.mapper = mapper;
    }

    public List<Card> getAll() {
        this.firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = firestoreDB.collection("cards").get();
        List<Card> cards = new ArrayList<>();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Card card = mapper.mapDocument2Card(document);
                cards.add(card);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return cards;
    }


}
