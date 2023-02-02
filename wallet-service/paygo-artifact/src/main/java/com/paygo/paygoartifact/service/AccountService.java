package com.paygo.paygoartifact.service;

import java.util.List;

import com.paygo.paygoartifact.dto.AccountDto;
import com.paygo.paygoartifact.dto.TransactionDto;
import com.paygo.paygoartifact.entity.Account;

public interface AccountService {
    List<Account> getAllAccounts();

    String createAccount(AccountDto accountDto);

    AccountDto getAccountByCIN(String cin);

    String deposit(TransactionDto transactionDto);

    String withdraw(TransactionDto transactionDto);
}
