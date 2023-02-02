package com.paygo.paygoartifact.restApi;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paygo.paygoartifact.dto.AccountDto;
import com.paygo.paygoartifact.dto.TransactionDto;
import com.paygo.paygoartifact.entity.Account;
import com.paygo.paygoartifact.service.AccountService;


import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<Account> getAllAccount(){
        return this.accountService.getAllAccounts();
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(this.accountService.createAccount(accountDto));
    }

    @GetMapping("/{cin}")
    public ResponseEntity<AccountDto> getAccountByCIN(@PathVariable String cin) {
        return ResponseEntity.ok(this.accountService.getAccountByCIN(cin));
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(this.accountService.deposit(transactionDto));
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(this.accountService.withdraw(transactionDto));
    }
}
