package com.inno.wallet.service.impl;

import com.inno.wallet.exception.NotEnoughMoneyException;
import com.inno.wallet.model.UserAccount;
import com.inno.wallet.repository.WalletRepository;
import com.inno.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Transactional
    @Override
    public void spend(Long userId, BigDecimal amount) {
        UserAccount account = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughMoneyException("Not enough money on balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
    }
}
