package com.paygo.paygoartifact.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.paygo.paygoartifact.entity.Wallet;
import org.springframework.stereotype.Service;

import com.paygo.paygoartifact.dto.AccountDto;
import com.paygo.paygoartifact.dto.TransactionDto;
import com.paygo.paygoartifact.repository.AccountRepository;
import com.paygo.paygoartifact.service.AccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public List< Wallet > getAllAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public String createAccount(AccountDto accountDto) {
        Optional< Wallet > accountOptional = this.accountRepository.findByCinClient(accountDto.getCinClient());

        if (accountOptional.isPresent()) {
            return "CIN : " + accountDto.getCinClient() + "  already exists";
        }

        // TODO CREATE GENERIC METHOD FOR MAPPING DTO TO ENTITY 
        try {
            if (accountDto.getCinClient() != null && !accountDto.getCinClient().isEmpty()) {
                Wallet wallet = new Wallet ();
                wallet.setCinClient(accountDto.getCinClient());
                
                if (accountDto.getBalance() != null && accountDto.getBalance() > -1) { // 0 is a null balance
                    wallet.setBalance(accountDto.getBalance());
                    if (accountDto.getOverdraftLimit() != null && accountDto.getOverdraftLimit() > 0) {
                        wallet.setOverdraftLimit(accountDto.getOverdraftLimit());
                    } else {
                        wallet.setOverdraftLimit(1000.00); // Default limit
                    }
                    
                    wallet.setStatus(true); //default statis if balance > -1
                }
    
                wallet.setCreatedAt(LocalDate.now());

                this.accountRepository.save( wallet );
                return "Wallet has ben created";
            }
        } catch (Exception exception){
            return exception.getMessage().toString();
        }
        return "something is wrong";
        
    }

    @Override
    public AccountDto getAccountByCIN(String cin) {
        if (cin != null && !cin.isEmpty()) {
            Optional< Wallet > accountOptional = this.accountRepository.findByCinClient(cin);
            
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

    // @Override
    // public String deposit(TransactionDto transactionDto) {
    //     if (transactionDto.getAmount() != null && transactionDto.getAmount() > 0) {
    //         Optional<Wallet> accountOptional = this.accountRepository.findByCinClient(transactionDto.getCin());

    //         if (accountOptional.isPresent()) {
    //             accountOptional.get().setBalance(accountOptional.get().getBalance() + transactionDto.getAmount());
    //             this.accountRepository.save(accountOptional.get());
    //             return "operation accomplished successfully";
    //         } else {
    //             return "Wallet with cin : " + transactionDto.getCin() + " is not exist";
    //         }
    //     }
    //     return "amount is not valid";
    // }

    

    @Override
    public String withdraw(TransactionDto transactionDto) {
        if (transactionDto.getAmount() != null && transactionDto.getAmount() > 0) {
            Optional< Wallet > accountOptional = this.accountRepository.findByCinClient(transactionDto.getCin());

            if (accountOptional.isPresent()) {
                if (accountOptional.get().getBalance() > transactionDto.getAmount()) {
                    accountOptional.get().setBalance(accountOptional.get().getBalance() - transactionDto.getAmount());
                    this.accountRepository.save(accountOptional.get());
                    return "operation accomplished successfully";
                } else {
                    return "The balance is less than expected";
                }
            } else {
                return "Wallet with cin : " + transactionDto.getCin() + " is not exist";
            }
        } 
        return "amount is not valid";
    }

    @Override
    public String deposit(AccountDto accountDto) {
        Optional<Wallet> walletOptional = accountRepository.findByCinClient(accountDto.getCinClient());

        if(walletOptional.isPresent()) {
            walletOptional.get().setBalance(accountDto.getBalance());
            accountRepository.save(walletOptional.get());
        }
        return null;
    }

    @Override
    public Optional< Wallet > getByCin( String cin) {
        return accountRepository.findByCinClient(cin);
        // return Optional.empty();
    }
    
}
