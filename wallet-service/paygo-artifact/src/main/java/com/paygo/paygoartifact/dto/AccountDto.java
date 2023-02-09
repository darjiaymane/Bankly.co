package com.paygo.paygoartifact.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountDto {
    private String cinClient;
    private Double balance;
    private Double overdraftLimit;
    private Boolean status;
    private LocalDate createdAt;
    private LocalDate deletedAt;
}