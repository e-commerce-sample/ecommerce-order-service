package com.ecommerce.order.order.model;

import com.ecommerce.common.event.DomainEventAwareAggregate;
import com.ecommerce.common.event.order.OrderAddressChangedEvent;
import com.ecommerce.common.event.order.OrderCreatedEvent;
import com.ecommerce.common.event.order.OrderPaidEvent;
import com.ecommerce.common.event.order.OrderProductChangedEvent;
import com.ecommerce.common.model.Address;
import com.ecommerce.order.order.exception.OrderCannotBeModifiedException;
import com.ecommerce.order.order.exception.PaidPriceNotSameWithOrderPriceException;
import com.ecommerce.order.order.exception.ProductNotInOrderException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.ecommerce.order.order.model.OrderStatus.CREATED;
import static com.ecommerce.order.order.model.OrderStatus.PAID;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.ZERO;
import static java.time.Instant.now;

public class Order extends DomainEventAwareAggregate {
    private OrderId id;
    private List<OrderItem> items = newArrayList();
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Address address;
    private Instant createdAt;

    private Order() {
    }

    private Order(OrderId id, List<OrderItem> items, Address address) {
        this.id = id;
        this.items.addAll(items);
        this.totalPrice = calculateTotalPrice();
        this.status = CREATED;
        this.address = address;
        this.createdAt = now();
        raiseEvent(new OrderCreatedEvent(id.toString(), totalPrice, address, createdAt));
    }

    public static Order create(OrderId id, List<OrderItem> items, Address address) {
        return new Order(id, items, address);
    }


    public void changeProductCount(ProductId productId, int count) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

        OrderItem orderItem = retrieveItem(productId);
        int originalCount = orderItem.getCount();
        orderItem.updateCount(count);
        this.totalPrice = calculateTotalPrice();
        raiseEvent(new OrderProductChangedEvent(id.toString(), productId.toString(), originalCount, count));
    }

    private BigDecimal calculateTotalPrice() {
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(ZERO, BigDecimal::add);
    }


    private OrderItem retrieveItem(ProductId productId) {
        return items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotInOrderException(productId, id));
    }

    public void pay(BigDecimal paidPrice) {
        if (!this.totalPrice.equals(paidPrice)) {
            throw new PaidPriceNotSameWithOrderPriceException(id);
        }
        this.status = PAID;
        raiseEvent(new OrderPaidEvent(this.getId().toString()));
    }

    public void changeAddressDetail(String detail) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

        this.address = this.address.changeDetailTo(detail);
        raiseEvent(new OrderAddressChangedEvent(getId().toString(), detail, address.getDetail()));
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

    public Address getAddress() {
        return address;
    }
}
