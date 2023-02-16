package com.bankly.userservice.dto;

import java.io.Serializable;

public class WalletDto implements Serializable {
    private String cinClient;
    private Double balance;
    private Double overdraftLimit;
    private Boolean status;

    public WalletDto(String cinClient, Double balance) {
        this.cinClient = cinClient;
    }

    public String getCinClient() {
        return cinClient;
    }

    public void setCinClient(String cinClient) {
        this.cinClient = cinClient;
    }

    public WalletDto(String cinClient) {
        this.cinClient = cinClient;
    }

    public WalletDto() {
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(Double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WalletDto{" +
                "cinClient='" + cinClient + '\'' +
                '}';
    }
}
