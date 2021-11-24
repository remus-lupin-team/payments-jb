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

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @DeleteMapping(path="{id}")
   public ResponseEntity<String> removeCard(@PathVariable String id){
      this.cardService.removeCard(id);
      return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/getAllCards")
    public List<Card> getAllCards(){
         return cardService.getAll();
    }

  public @PutMapping(path="{id}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<String> updateCard(@PathVariable String id, @RequestBody Card cardDetails){
        this.cardService.updateCard(id, cardDetails);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/addCard")
    ResponseEntity<Object> addCard(@RequestBody Card card){
        cardService.addCard(card);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PutMapping("/preferredCard")
    ResponseEntity<Object> setPreferredCard(@RequestParam String cardNumber){
        return new ResponseEntity<>(cardService.setPreferredCard(cardNumber), HttpStatus.OK);
    }
}
