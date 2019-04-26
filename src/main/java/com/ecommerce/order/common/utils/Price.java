package com.ecommerce.order.common.utils;


import java.math.BigDecimal;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;

public class Price {
    private static final int DEFAULT_SCALE = 2;
    private static final int DIVIDE_SCALE = 10;
    private Currency currency;
    private BigDecimal amount;

    private Price() {
    }

    private Price(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount.setScale(DEFAULT_SCALE, HALF_UP);
    }

    public static Price of(Currency currency, BigDecimal amount) {
        return new Price(currency, amount);
    }

    public Price to(Currency currency) {
        BigDecimal relativeRate = this.currency.getBaseRate().divide(currency.getBaseRate(), DIVIDE_SCALE, HALF_UP);
        return of(currency, this.amount.divide(relativeRate, DIVIDE_SCALE, HALF_UP));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        return amount.equals(price.to(this.currency).amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
