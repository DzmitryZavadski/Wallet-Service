package com.inno.wallet.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SpendRequest(
        @NotNull Long userId,
        @NotNull @Positive BigDecimal amount
) {
}
