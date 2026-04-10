package com.inno.wallet.controller;

import com.inno.wallet.model.dto.SpendRequest;
import com.inno.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/spend")
    public ResponseEntity<Void> spend(@RequestBody @Valid SpendRequest request) {
        walletService.spend(request.userId(), request.amount());
        return ResponseEntity.ok().build();
    }
}
