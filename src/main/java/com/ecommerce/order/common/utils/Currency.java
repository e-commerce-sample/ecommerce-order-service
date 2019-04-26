package com.ecommerce.order.common.utils;

import java.math.BigDecimal;

public enum Currency {
    CNY(BigDecimal.valueOf(11.2)),
    USD(BigDecimal.valueOf(1.5)),
    EUR(BigDecimal.valueOf(1));

    private final BigDecimal baseRate;

    Currency(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }
}
