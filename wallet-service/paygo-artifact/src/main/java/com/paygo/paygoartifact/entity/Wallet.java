package com.paygo.paygoartifact.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_account;
    @Column(name = "cin_client")
    private String cinClient;
    private Double balance;
    private Double overdraftLimit;
    private Boolean status;
    private LocalDate createdAt;
    private LocalDate deletedAt;
}