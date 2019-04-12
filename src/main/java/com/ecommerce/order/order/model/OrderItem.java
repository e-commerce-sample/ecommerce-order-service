package com.ecommerce.order.order.model;

import com.ecommerce.order.product.ProductId;

import java.math.BigDecimal;

public class OrderItem {
    private ProductId productId;
    private int count;
    private BigDecimal price;
}
