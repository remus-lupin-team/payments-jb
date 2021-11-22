package com.bootcamp.demo.controller;

import com.bootcamp.demo.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/card", produces = APPLICATION_JSON_VALUE )
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @DeleteMapping(path="{id}")
    ResponseEntity<String> removeCard(@PathVariable String id){
      this.cardService.removeCard(id);
      return new ResponseEntity<>("Successfully deleted card "+ id, HttpStatus.OK);
    }
}
