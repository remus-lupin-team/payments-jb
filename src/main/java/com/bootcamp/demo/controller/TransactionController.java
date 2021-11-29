package com.bootcamp.demo.controller;

import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.time.LocalDate;
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
    public List<Transaction> getAll() {
        System.out.println(transactionService.getAll());
        return transactionService.getAll();
    }

    @GetMapping("/amount")
    public List<Transaction> filterByAmount(double minAmount, double maxAmount) {
        System.out.println(transactionService.filterByAmount(minAmount, maxAmount));
        return transactionService.filterByAmount(minAmount, maxAmount);
    }

    @GetMapping("/card")
    public List<Transaction> filterByCardNr(String cardNr) {
        System.out.println(transactionService.filterByCardNr(cardNr));
        return transactionService.filterByCardNr(cardNr);
    }

    @GetMapping("/date")
    public List<Transaction> filterByDate(LocalDate startDate, LocalDate endDate) {
        System.out.println(transactionService.filterByDate(startDate, endDate));
        return transactionService.filterByDate(startDate, endDate);
    }

    @GetMapping("/details")
    public List<Transaction> getTransactionDetails() {
        return transactionService.getTransactionDetails();
    }

    @GetMapping("/total")
    public String getAccountStatement(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                                      @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        return transactionService.getFormattedAccountStatement(startDate, endDate);
    }

    @GetMapping("/total/week")
    public String getLastWeekAccountStatement() {
        return transactionService.getFormattedAccountStatement(LocalDate.now().minusDays(7), LocalDate.now());
    }

    @GetMapping("/total/month")
    public String getLastMonthAccountStatement() {
        return transactionService.getFormattedAccountStatement(LocalDate.now().minusMonths(1), LocalDate.now());
    }

}
