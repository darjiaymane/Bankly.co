package com.paygo.paygoartifact.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paygo.paygoartifact.dto.AccountDto;
import com.paygo.paygoartifact.dto.TransactionDto;
import com.paygo.paygoartifact.entity.Account;
import com.paygo.paygoartifact.repository.AccountRepository;
import com.paygo.paygoartifact.service.AccountService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public String createAccount(AccountDto accountDto) {
        Optional<Account> accountOptional = this.accountRepository.findByCinClient(accountDto.getCinClient());

        if (accountOptional.isPresent()) {
            return "CIN : " + accountDto.getCinClient() + "  already exists";
        }

        // TODO CREATE GENERIC METHOD FOR MAPPING DTO TO ENTITY 
        try {
            if (accountDto.getCinClient() != null && !accountDto.getCinClient().isEmpty()) {
                Account account = new Account();
                account.setCinClient(accountDto.getCinClient());
                
                if (accountDto.getBalance() != null && accountDto.getBalance() > -1) { // 0 is a null balance
                    account.setBalance(accountDto.getBalance());
                    if (accountDto.getOverdraftLimit() != null && accountDto.getOverdraftLimit() > 0) {
                        account.setOverdraftLimit(accountDto.getOverdraftLimit());
                    } else {
                        account.setOverdraftLimit(1000.00); // Default limit
                    }
                    
                    account.setStatus(true); //default statis if balance > -1
                }
    
                account.setCreatedAt(LocalDate.now());

                this.accountRepository.save(account);
                return "Account has ben created";
            } 
        } catch (Exception exception){
            return exception.getMessage().toString();
        }
        return "something is wrong";
        
    }

    @Override
    public AccountDto getAccountByCIN(String cin) {
        if (cin != null && !cin.isEmpty()) {
            Optional<Account> accountOptional = this.accountRepository.findByCinClient(cin);
            
            if (accountOptional.isPresent()) {
                AccountDto accountDto = new AccountDto();

                accountDto.setBalance(accountOptional.get().getBalance());
                accountDto.setCinClient(accountOptional.get().getCinClient());
                accountDto.setCreatedAt(accountOptional.get().getCreatedAt());
                accountDto.setStatus(accountOptional.get().getStatus());
                accountDto.setDeletedAt(accountOptional.get().getDeletedAt());
                accountDto.setOverdraftLimit(accountOptional.get().getOverdraftLimit());
                return accountDto;
            }
        }
        return null;
    }

    @Override
    public String deposit(TransactionDto transactionDto) {
        if (transactionDto.getAmount() != null && transactionDto.getAmount() > 0) {
            Optional<Account> accountOptional = this.accountRepository.findByCinClient(transactionDto.getCin());

            if (accountOptional.isPresent()) {
                accountOptional.get().setBalance(accountOptional.get().getBalance() + transactionDto.getAmount());
                this.accountRepository.save(accountOptional.get());
                return "operation accomplished successfully";
            } else {
                return "Account with cin : " + transactionDto.getCin() + " is not exist";
            }
        }
        return "amount is not valid";
    }

    @Override
    public String withdraw(TransactionDto transactionDto) {
        if (transactionDto.getAmount() != null && transactionDto.getAmount() > 0) {
            Optional<Account> accountOptional = this.accountRepository.findByCinClient(transactionDto.getCin());

            if (accountOptional.isPresent()) {
                if (accountOptional.get().getBalance() > transactionDto.getAmount()) {
                    accountOptional.get().setBalance(accountOptional.get().getBalance() - transactionDto.getAmount());
                    this.accountRepository.save(accountOptional.get());
                    return "operation accomplished successfully";
                } else {
                    return "The balance is less than expected";
                }
                
            } else {
                return "Account with cin : " + transactionDto.getCin() + " is not exist";
            }
        } 
        return "amount is not valid";
    }
    
}
