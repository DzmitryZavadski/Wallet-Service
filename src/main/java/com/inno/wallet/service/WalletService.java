package com.inno.wallet.service;

import java.math.BigDecimal;

public interface WalletService {
    void spend(Long userId, BigDecimal amount);
}
