package com.bootcamp.demo.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String id;
    private String userId;
    private String cardNumber;
    private double amount;
    private LocalDateTime timestamp;

}
