package com.inno.wallet.service.impl;

import com.inno.wallet.exception.NotEnoughMoneyException;
import com.inno.wallet.exception.UserNotFoundException;
import com.inno.wallet.model.UserAccount;
import com.inno.wallet.repository.WalletRepository;
import com.inno.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Transactional
    @Override
    public void spend(Long userId, BigDecimal amount) {
        UserAccount account = walletRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughMoneyException("Operation failed: your balance is " + account.getBalance()
                    + ", but you tried to spend " + amount);
        }

        account.setBalance(account.getBalance().subtract(amount));
        walletRepository.save(account);
    }
}
