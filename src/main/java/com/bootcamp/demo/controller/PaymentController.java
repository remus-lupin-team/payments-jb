package com.bootcamp.demo.controller;

import com.bootcamp.demo.exception.CardNotFoundException;
import com.bootcamp.demo.exception.PaymentFailException;
import com.bootcamp.demo.model.PaymentRequest;
import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public Transaction pay(@RequestBody PaymentRequest paymentRequest) throws PaymentFailException, CardNotFoundException {
        return paymentService.processPayment(paymentRequest.getCardNumber(), paymentRequest.getAmount());
    }

    @PostMapping("/pay-with-preferred-card")
    public Transaction payWithPreferredCard(@RequestBody Double amount){
        return paymentService.processPaymentWithPreferredCard(amount);
    }
}
