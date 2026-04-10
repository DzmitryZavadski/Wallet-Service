package com.inno.wallet.service.impl;

import com.inno.wallet.exception.NotEnoughMoneyException;
import com.inno.wallet.model.UserAccount;
import com.inno.wallet.repository.UserAccountRepository;
import com.inno.wallet.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional
    @Override
    public void spend(Long userId, BigDecimal amount) {
        UserAccount account = userAccountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughMoneyException("Not enough money on balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
    }
}
