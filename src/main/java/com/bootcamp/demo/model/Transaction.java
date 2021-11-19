package com.bootcamp.demo.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String id;
    private String cardNumber;
    private Double amount;
    private LocalDateTime timestamp;
}
