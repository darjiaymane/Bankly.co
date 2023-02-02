package com.paygo.paygoartifact.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Double amount;
    private String cin;
}
