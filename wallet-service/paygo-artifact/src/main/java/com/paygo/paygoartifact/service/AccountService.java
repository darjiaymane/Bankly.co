package com.paygo.paygoartifact.service;

import java.util.List;
import java.util.Optional;

import com.paygo.paygoartifact.dto.AccountDto;
import com.paygo.paygoartifact.dto.TransactionDto;
import com.paygo.paygoartifact.entity.Wallet;

public interface AccountService {
    List< Wallet > getAllAccounts();

    String createAccount(AccountDto accountDto);

    AccountDto getAccountByCIN(String cin);

    // String deposit(TransactionDto transactionDto);
    String deposit(AccountDto accountDto);

    String withdraw(TransactionDto transactionDto);

    Optional< Wallet > getByCin( String cin);
}
