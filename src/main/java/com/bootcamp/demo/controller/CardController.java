package com.bootcamp.demo.controller;

import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/card", produces = APPLICATION_JSON_VALUE)
public class CardController {
    private final CardService cardService;
    private final CardValidation cardValidation;

    @Autowired
    public CardController(CardService cardService, CardValidation cardValidation) {
        this.cardService = cardService;
        this.cardValidation=cardValidation;
    }

    @DeleteMapping(path="{cardNumber}")
   public ResponseEntity<String> removeCard(@PathVariable String cardNumber){
      this.cardService.removeCard(cardNumber);
      return new ResponseEntity<>(cardNumber, HttpStatus.OK);
    }

    @GetMapping("/getAllCards")
    public List<Card> getAllCards(){
         return cardService.getAll();
    }

  public @PutMapping(path="{cardNumber}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<String> updateCard(@PathVariable String cardNumber, @RequestBody Card cardDetails){
        this.cardService.updateCard(cardNumber, cardDetails);
        return new ResponseEntity<>(cardNumber, HttpStatus.OK);
    }

    @PostMapping("/addCard")
    ResponseEntity<Object> addCard(@RequestBody Card card){
        if (cardValidation.isValidCard(card)){
            return new ResponseEntity<>(cardService.addCard(card), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/preferredCard")
    ResponseEntity<Object> setPreferredCard(@RequestParam String cardNumber){
        return new ResponseEntity<>(cardService.setPreferredCard(cardNumber), HttpStatus.OK);
    }
}
