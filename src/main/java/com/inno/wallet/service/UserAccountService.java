package com.inno.wallet.service;

import java.math.BigDecimal;

public interface UserAccountService {
    void spend(Long userId, BigDecimal amount);
}
