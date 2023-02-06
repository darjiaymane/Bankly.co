package com.paygo.paygo.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paygo.paygo.dto.TransactionDto;
import com.paygo.paygo.entity.Transaction;
import com.paygo.paygo.service.TransactionService;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/api/transaction")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransaction() {
        return transactionService.getAll();
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDto  transactionDto) {
        return ResponseEntity.ok( this.transactionService.createTransaction(transactionDto));
    }
}
