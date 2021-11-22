package com.bootcamp.demo.controller;

import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.service.CardService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    private Firestore firestoreDB;
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @DeleteMapping(value = "/{cardNumber}")
    public String removeCard(@PathVariable String cardNumber){
        this.firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = firestoreDB.collection("cards").document(cardNumber).delete();
        return "Successfully deleted " + cardNumber;
    }

    @GetMapping("/getAllCards")
    @ResponseBody
    public List<Card> getAllCards(){
         return cardService.getAll();
    }
}
