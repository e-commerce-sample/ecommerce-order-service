package com.ecommerce.order.common.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceTest {

    @Test
    public void should_equal() {
        Price cny = Price.of(Currency.CNY, BigDecimal.valueOf(22.4));
        Price euro = Price.of(Currency.EUR, BigDecimal.valueOf(2));
        assertEquals(cny, euro);

    }

    @Test
    public void should_transform_to_another_currency() {
        Price cny = Price.of(Currency.CNY, BigDecimal.valueOf(22.4));
        Price euro = cny.to(Currency.EUR);
        assertEquals(Price.of(Currency.EUR, BigDecimal.valueOf(2)), euro);
    }

}