package com.ecommerce.order.order.model;

import com.ecommerce.order.product.ProductId;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Order {
    private OrderId id;
    private List<OrderItem> items = newArrayList();
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Instant createdAt;

    public void removeProduct(ProductId productId) {
        if (status == OrderStatus.CREATED) {
            // re-calculate total price
        }

        throw new RuntimeException();
    }

}
