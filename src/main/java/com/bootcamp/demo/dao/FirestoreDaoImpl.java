package com.bootcamp.demo.dao;

import com.bootcamp.demo.mapper.DocumentToCardMapper;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.CardStateEnum;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static java.lang.System.getProperty;
import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
public class FirestoreDaoImpl implements FirestoreDao {
    public static final String CARDS = "cards";
    private Firestore firestoreDB;
    private final DocumentToCardMapper mapper;

    @Autowired
    public FirestoreDaoImpl(DocumentToCardMapper mapper) {
        this.mapper = mapper;
    }

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

    @Override
    public List<Card> getAll() {
        ApiFuture<QuerySnapshot> query = firestoreDB.collection(CARDS).get();
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

    @Override
    public void addCard(Card card) {
        ApiFuture<QuerySnapshot> findAllQuery = firestoreDB.collection(CARDS).get();
        try {
        Map<String, Object> cardData = new HashMap<>();
        cardData.put("cardNumber", card.getCardNumber());
        cardData.put("holderName", card.getHolderName());
        cardData.put("CVV", card.getCVV());
        cardData.put("state", findAllQuery.get().getDocuments().isEmpty()
                ? CardStateEnum.PREFERRED
                : CardStateEnum.NONE);
        cardData.put("expirationYear", card.getExpirationYear());
        cardData.put("expirationMonth", card.getExpirationMonth());

        ApiFuture<WriteResult> document = firestoreDB.collection(CARDS).document().set(cardData);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card setPreferredCard(String cardNumber) {
        CollectionReference ref = firestoreDB.collection(CARDS);
        Query stateQuery = ref.whereEqualTo("state", CardStateEnum.PREFERRED);
        ApiFuture<QuerySnapshot> stateQuerySnapshot = stateQuery.get();

        Query cardNumberQuery = ref.whereEqualTo("cardNumber", cardNumber);
        ApiFuture<QuerySnapshot> cardNumberSnapshot = cardNumberQuery.get();

        Card card = new Card();
        try {
            //do this only if the card that wants to be set exists
            if (!cardNumberSnapshot.get().getDocuments().isEmpty()) {
                //set any pre-existing preferred card to Not preferred
                for (DocumentSnapshot document : stateQuerySnapshot.get().getDocuments()) {
                    firestoreDB.collection(CARDS).document(document.getId()).update("state", CardStateEnum.NONE);
                }
                //set preferred to the selected card
                QueryDocumentSnapshot document = cardNumberSnapshot.get().getDocuments().get(0);
                firestoreDB.collection(CARDS).document(document.getId()).update("state", CardStateEnum.PREFERRED);
                card = mapper.mapDocument2Card(document);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return card;
    }
}
