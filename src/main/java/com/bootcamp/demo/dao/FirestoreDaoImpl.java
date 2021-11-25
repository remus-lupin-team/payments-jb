package com.bootcamp.demo.dao;

import com.bootcamp.demo.mapper.DocumentToCardMapper;
import com.bootcamp.demo.mapper.DocumentToTransactionMapper;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.Transaction;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.System.getProperty;
import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
public class FirestoreDaoImpl implements FirestoreDao {
    private Firestore firestoreDB;
    private final DocumentToCardMapper mapperToCard;
    private final DocumentToTransactionMapper mapperToTransaction;

    @Autowired
    public FirestoreDaoImpl(DocumentToCardMapper mapperToCard, DocumentToTransactionMapper mapperToTransaction) {
        this.mapperToCard = mapperToCard;
        this.mapperToTransaction = mapperToTransaction;
    }

    @PostConstruct
    private void initFirestore() throws IOException {
        InputStream serviceAccount = new ByteArrayInputStream(getProperty("firebaseKey").getBytes(UTF_8));
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
        if (FirebaseApp.getApps().size() == 0)
            FirebaseApp.initializeApp(options);

        firestoreDB = FirestoreClient.getFirestore();
    }

    @Override
    public void remove(String id, String collectionName) {
        firestoreDB.collection(collectionName).document(id).delete();
    }

    @Override
    public void update(String id, String collectionName, Map cardDetails) {
        firestoreDB.collection(collectionName).document(id).set(cardDetails);
    }

    @Override
    public List<Card> getAll(){
        ApiFuture<QuerySnapshot> query = firestoreDB.collection("cards").get();
        List<Card> cards = new ArrayList<>();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Card card = mapperToCard.mapDocument2Card(document);
                cards.add(card);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return cards;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        ApiFuture<QuerySnapshot> query = firestoreDB.collection("transactions").get();
        List<Transaction> objects = new ArrayList<Transaction>();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Transaction obj = mapperToTransaction.mapDocument2Transaction(document);
                objects.add(obj);

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
