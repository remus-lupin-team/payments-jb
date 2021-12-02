package com.bootcamp.demo.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/card")
public class CardController {
    private Firestore firestoreDB;

    @DeleteMapping(value = "/{cardNumber}")
    public String removeCard(@PathVariable String cardNumber){
        this.firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = firestoreDB.collection("cards").document(cardNumber).delete();
        return "Successfully deleted " + cardNumber;
    }
}
