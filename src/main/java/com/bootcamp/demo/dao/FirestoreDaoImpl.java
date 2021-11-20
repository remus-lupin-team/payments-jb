package com.bootcamp.demo.dao;

import com.bootcamp.demo.model.Card;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static java.lang.System.getProperty;
import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
public class FirestoreDaoImpl implements FirestoreDao{
    private Firestore firestoreDB;

    @PostConstruct
    private void initFirestore() throws IOException {
        InputStream serviceAccount = new ByteArrayInputStream(getProperty("firebaseKey").getBytes(UTF_8));
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
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
}
