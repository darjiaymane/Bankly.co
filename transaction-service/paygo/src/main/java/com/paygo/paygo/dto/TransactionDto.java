package com.paygo.paygo.dto;

import java.time.LocalDate;

import com.paygo.paygo.utils.Kind;

import lombok.Data;

@Data
public class TransactionDto {
    private Double amount;
    private Kind kind;
    private LocalDate createdAt;
    private String cin;
}
