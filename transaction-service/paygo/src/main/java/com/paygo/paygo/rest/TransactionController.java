package com.paygo.paygo.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{cin}")
    public List<Transaction> getTransactionsByCin(@PathVariable String cin){
        return transactionService.getTransactionsByCin(cin);
    }
}
