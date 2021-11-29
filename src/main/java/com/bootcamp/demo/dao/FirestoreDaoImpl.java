package com.bootcamp.demo.dao;

import com.bootcamp.demo.exception.CardNotFoundException;
import com.bootcamp.demo.exception.FirestoreDaoException;
import com.bootcamp.demo.mapper.DocumentToCardMapper;
import com.bootcamp.demo.mapper.DocumentToTransactionMapper;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.CardStateEnum;
import com.bootcamp.demo.model.Transaction;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static java.lang.System.getProperty;
import static java.nio.charset.StandardCharsets.UTF_8;

@ConditionalOnProperty("firebaseKey")
@Repository
public class FirestoreDaoImpl implements FirestoreDao {
    public static final String CARDS_COLLECTION = "cards";
    private Firestore firestoreDB;
    private final DocumentToCardMapper mapperToCard;
    private final DocumentToTransactionMapper mapperToTransaction;
    private static final Logger LOGGER = LoggerFactory.getLogger(FirestoreDaoImpl.class);

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
        FirebaseApp.initializeApp(options);

        firestoreDB = FirestoreClient.getFirestore();
    }

    @Override
    public void remove(String cardNumber, String collectionName) {
        CollectionReference ref = firestoreDB.collection(collectionName);
        Query cardNumberQuery = ref.whereEqualTo("cardNumber", cardNumber);
        ApiFuture<QuerySnapshot> cardNumberQuerySnapshot = cardNumberQuery.get();
        try {
            QuerySnapshot querySnapshot = cardNumberQuerySnapshot.get();
            String documentId = querySnapshot.getDocuments().get(0).getId();
            firestoreDB.collection(collectionName).document(documentId).delete();
        }
        catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Failed to remove card", e);
        }
    }

    @Override
    public void update(String cardNumber, String collectionName, Map cardDetails) {
        CollectionReference ref = firestoreDB.collection(collectionName);
        Query cardNumberQuery = ref.whereEqualTo("cardNumber", cardNumber);
        ApiFuture<QuerySnapshot> cardNumberQuerySnapshot = cardNumberQuery.get();
        try {
            QuerySnapshot querySnapshot = cardNumberQuerySnapshot.get();
            String documentId = querySnapshot.getDocuments().get(0).getId();
            firestoreDB.collection(collectionName).document(documentId).set(cardDetails);
        }
        catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Failed to update card", e);
        }
    }

    @Override
    public List<Card> getAll() {
        ApiFuture<QuerySnapshot> query = firestoreDB.collection(CARDS_COLLECTION).get();
        List<Card> cards = new ArrayList<>();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Card card = mapperToCard.mapDocument2Card(document);
                cards.add(card);
            }
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Failed to add a card", e);
        }
        return cards;
    }

    @Override
    public Card addCard(Card card) {
        ApiFuture<QuerySnapshot> findAllQuery = firestoreDB.collection(CARDS_COLLECTION).get();
        Card cardToReturn = new Card();
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

            cardToReturn = card;
            cardToReturn.setState(findAllQuery.get().getDocuments().isEmpty()
                    ? CardStateEnum.PREFERRED
                    : CardStateEnum.NONE);

            ApiFuture<WriteResult> document = firestoreDB.collection(CARDS_COLLECTION).document().set(cardData);

        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Failed to add a card", e);
        }
        return cardToReturn;
    }

    @Override
    public Card getCardById(String cardId) throws FirestoreDaoException, CardNotFoundException {
        ApiFuture<DocumentSnapshot> query = firestoreDB.collection(CARDS_COLLECTION).document(cardId).get();
        Card card;
        try {
            DocumentSnapshot document = query.get();
            if(!document.exists()) {
                throw new CardNotFoundException("Failed to get card by id " + cardId);
            }
            card = mapperToCard.mapDocument2Card(document);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Failed to get card by id", e);
            throw new FirestoreDaoException("Failed to get card by id", e);
        }

        return card;
    }

    @Override
    public Card setPreferredCard(String cardNumber) {
        CollectionReference ref = firestoreDB.collection(CARDS_COLLECTION);
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
                    firestoreDB.collection(CARDS_COLLECTION).document(document.getId()).update("state", CardStateEnum.NONE);
                }
                //set preferred to the selected card
                QueryDocumentSnapshot document = cardNumberSnapshot.get().getDocuments().get(0);
                firestoreDB.collection(CARDS_COLLECTION).document(document.getId()).update("state", CardStateEnum.PREFERRED);
                card = mapperToCard.mapDocument2Card(document);
                card.setState(CardStateEnum.PREFERRED);
            }
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Failed to add a card", e);
        }
        return card;
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

    @Override
    public Transaction addTransaction(Transaction transaction) {
        Map<String, Object> transactionsData = new HashMap<>();
        transactionsData.put("cardNumber", transaction.getCardNumber());
        transactionsData.put("amount", transaction.getAmount());
        transactionsData.put("timestamp", transaction.getTimestamp().toEpochSecond(ZoneOffset.UTC));

        ApiFuture<WriteResult> document = firestoreDB.collection("transactions").document().set(transactionsData);
        return transaction;
    }
}
