package com.bankly.userservice.controllers;

import com.bankly.userservice.dto.WalletDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "WALLET-SERVICE/api/account")
public interface WalletProxy {
    @PostMapping()
    String addWallet(@RequestBody WalletDto walletDto);
}
