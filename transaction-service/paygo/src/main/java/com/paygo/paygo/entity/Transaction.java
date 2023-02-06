package com.paygo.paygo.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.paygo.paygo.utils.Kind;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    private String operationRef;
    private Double amount;
    private Kind kind;
    private LocalDate createdAt;
    private String cin;
}
