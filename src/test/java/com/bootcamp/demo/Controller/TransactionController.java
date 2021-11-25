package com.bootcamp.demo.Controller;


import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/history", produces = APPLICATION_JSON_VALUE)
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/all")
    public List<Transaction> getAll(){
        return transactionService.getAll();
    }

    @RequestMapping("/all")
    public String all() {
        return "transactionHistory";
    }
}

