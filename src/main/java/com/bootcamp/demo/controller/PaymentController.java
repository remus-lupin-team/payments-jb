package com.bootcamp.demo.controller;

import com.bootcamp.demo.exception.PaymentFailException;
import com.bootcamp.demo.model.Card;
import com.bootcamp.demo.model.PaymentDetailsDTO;
import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/test")
    public Transaction getTestTransaction(){
        Transaction transaction = new Transaction("1", "123435", "657353845", 234.43,  LocalDateTime.now());
        return transaction;
    }

    @PostMapping("/pay")
    public Transaction pay(@RequestBody PaymentDetailsDTO paymentDetails) throws PaymentFailException {
        return paymentService.simulatePayment(paymentDetails.getCard(), paymentDetails.getAmount());
    }

}
