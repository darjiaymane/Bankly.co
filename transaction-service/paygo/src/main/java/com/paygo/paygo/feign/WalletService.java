package com.paygo.paygo.feign;

import com.paygo.paygo.dto.WalletDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "WALLET-SERVICE")
public interface WalletService {

    @GetMapping("/api/account")
    public List < WalletDto > getAllAccount();

    @GetMapping("/api/account/{cin}")
    public WalletDto getWalletByCin(@PathVariable(value = "cin") final String cin);

    @PutMapping("/api/account/deposit")
    ResponseEntity<String> updateWallet( @RequestBody WalletDto walletDto);
}
