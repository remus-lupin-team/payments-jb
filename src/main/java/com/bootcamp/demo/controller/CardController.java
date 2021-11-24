package com.bootcamp.demo.controller;

import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/card", produces = APPLICATION_JSON_VALUE )
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @DeleteMapping(path="{id}")
   public ResponseEntity<String> removeCard(@PathVariable String id){
      this.cardService.removeCard(id);
      return new ResponseEntity<>("Successfully deleted card "+ id, HttpStatus.OK);
    }

  public @PutMapping(path="{id}", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<String> updateCard(@PathVariable String id, @RequestBody Card cardDetails){
        Map updateDetails = new HashMap();
        updateDetails.put("cardNumber", cardDetails.getCardNumber());
        updateDetails.put("holderName", cardDetails.getHolderName());
        updateDetails.put("cvv", cardDetails.getCVV());
        updateDetails.put("expirationYear", cardDetails.getExpirationYear());
        updateDetails.put("expirationMonth", cardDetails.getExpirationMonth());
        this.cardService.updateCard(id, updateDetails);
        return new ResponseEntity<>("Successfully updated card "+ id, HttpStatus.OK);
    }
}
