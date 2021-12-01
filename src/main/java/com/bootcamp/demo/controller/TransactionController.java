package com.bootcamp.demo.controller;

import com.bootcamp.demo.mapper.TransactionDtoMapper;
import com.bootcamp.demo.model.Transaction;
import com.bootcamp.demo.model.TransactionDTO;
import com.bootcamp.demo.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/history", produces = APPLICATION_JSON_VALUE)
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionDtoMapper transactionDtoMapper;

    public TransactionController(TransactionService transactionService, TransactionDtoMapper transactionDtoMapper) {
        this.transactionService = transactionService;
        this.transactionDtoMapper = transactionDtoMapper;
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

    @GetMapping
    public ModelAndView getTransactionHistory() {
        List<Transaction> transactions = transactionService.getAll();
        double totalExpenses = transactions.stream().mapToDouble(Transaction::getAmount).sum();

        List<TransactionDTO> transactionDTOS = transactions.stream()
                .map(transactionDtoMapper::transactionToTransactionDto)
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("totalExpenses", totalExpenses);
        modelAndView.addObject("transactions", transactionDTOS);
        modelAndView.setViewName("transactionHistory");
        return modelAndView;
    }
}