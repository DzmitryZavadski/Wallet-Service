package com.inno.wallet.model.dto;

import jakarta.validation.constraints.Positive;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

public record SpendRequest(
        @NotNull Long userId,
        @NotNull @Positive BigDecimal amount
) {
}
