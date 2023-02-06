package com.paygo.paygo.service;

import java.util.List;

import com.paygo.paygo.dto.TransactionDto;
import com.paygo.paygo.entity.Transaction;

public interface TransactionService {
    List<Transaction>  getAll();
    String createTransaction(TransactionDto  transactionDto);
}
