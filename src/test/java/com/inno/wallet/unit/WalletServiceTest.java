package com.inno.wallet.unit;

import com.inno.wallet.exception.NotEnoughMoneyException;
import com.inno.wallet.model.UserAccount;
import com.inno.wallet.repository.WalletRepository;
import com.inno.wallet.service.impl.WalletServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {
    @Mock
    private WalletRepository repository;

    @InjectMocks
    private WalletServiceImpl walletServiceImpl;

    @Test
    void spend_Success_WhenFundsAvailable() {
        Long userId = 1L;
        BigDecimal initialBalance = new BigDecimal("100.00");
        BigDecimal spendAmount = new BigDecimal("40.00");
        UserAccount account = new UserAccount(userId, "TestUser", initialBalance);

        when(repository.findById(userId)).thenReturn(Optional.of(account));

        walletServiceImpl.spend(userId, spendAmount);

        assertEquals(new BigDecimal("60.00"), account.getBalance());
        verify(repository, times(1)).findById(userId);
    }

    @Test
    void spend_ThrowsException_WhenInsufficientFunds() {
        Long userId = 1L;
        UserAccount account = new UserAccount(userId, "TestUser", new BigDecimal("10.00"));

        when(repository.findById(userId)).thenReturn(Optional.of(account));

        assertThrows(NotEnoughMoneyException.class, () ->
                walletServiceImpl.spend(userId, new BigDecimal("50.00"))
        );
    }

}
