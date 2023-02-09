package com.paygo.paygo.dto;

import lombok.Data;

@Data
public class WalletDto {
    private String cinClient;
    private Double balance;
    private Double overdraftLimit;
    private Boolean status;
}
