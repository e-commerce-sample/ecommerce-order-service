package com.ecommerce.order.order.model;

import com.ecommerce.order.common.ddd.AggregateRoot;
import com.ecommerce.order.order.exception.OrderCannotBeModifiedException;
import com.ecommerce.order.order.exception.PaidPriceNotSameWithOrderPriceException;
import com.ecommerce.order.order.exception.ProductNotInOrderException;
import com.ecommerce.order.product.ProductId;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.ecommerce.order.order.model.OrderStatus.CREATED;
import static com.ecommerce.order.order.model.OrderStatus.PAID;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.ZERO;
import static java.time.Instant.now;

public class Order implements AggregateRoot {
    private OrderId id;
    private List<OrderItem> items = newArrayList();
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Instant createdAt;

    private Order() {
    }

    private Order(OrderId id, List<OrderItem> items) {
        this.id = id;
        this.items.addAll(items);
        this.totalPrice = calculateTotalPrice();
        this.status = CREATED;
        this.createdAt = now();
    }

    public static Order create(OrderId id, List<OrderItem> items) {
        return new Order(id, items);
    }

    private BigDecimal calculateTotalPrice() {
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(ZERO, BigDecimal::add);

    }

    public void updateProductCount(ProductId productId, int count) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }
        OrderItem orderItem = items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotInOrderException(productId, id));

        orderItem.updateCount(count);

        this.totalPrice = calculateTotalPrice();
    }

    public void pay(BigDecimal paidPrice) {
        if (!this.totalPrice.equals(paidPrice)) {
            throw new PaidPriceNotSameWithOrderPriceException(id);
        }
        this.status = PAID;
    }

    public OrderId getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
