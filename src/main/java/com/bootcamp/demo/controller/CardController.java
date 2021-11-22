package com.bootcamp.demo.controller;

import com.bootcamp.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    ResponseEntity<String> removeCard(@PathVariable String id){
        this.cardService.removeCard(id);
        return new ResponseEntity<>("Successfully deleted card "+ id, HttpStatus.OK);
    }

    @GetMapping("/getAllCards")
    public List<Card> getAllCards(){
         return cardService.getAll();
    }

    @PutMapping(path="{id}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<String> updateCard(@PathVariable String id, @RequestBody Map cardDetails){
        this.cardService.updateCard(id, cardDetails);
        return new ResponseEntity<>("Successfully updated card "+ id, HttpStatus.OK);
    }
}
