package com.paygo.paygo.rest;

import com.paygo.paygo.dto.WalletDto;
import com.paygo.paygo.feign.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restapi/account")
@EnableFeignClients
@AllArgsConstructor
public class AccountControllerAPIs {
    private final WalletService walletService;

    @GetMapping
    public List< WalletDto > getAllAccountFromRestApiService() {
        return this.walletService.getAllAccount ();
    }
}
