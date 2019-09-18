package com.ecommerce.order.order.model;

import com.ecommerce.order.order.exception.OrderCannotBeModifiedException;
import com.ecommerce.order.order.exception.PaidPriceNotSameWithOrderPriceException;
import com.ecommerce.order.order.exception.ProductNotInOrderException;
import com.ecommerce.order.sdk.event.order.OrderAddressChangedEvent;
import com.ecommerce.order.sdk.event.order.OrderCreatedEvent;
import com.ecommerce.order.sdk.event.order.OrderPaidEvent;
import com.ecommerce.order.sdk.event.order.OrderProductChangedEvent;
import com.ecommerce.order.sdk.representation.order.OrderRepresentation;
import com.ecommerce.order.sdk.representation.order.OrderSummaryRepresentation;
import com.ecommerce.shared.model.Address;
import com.ecommerce.shared.model.BaseAggregate;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.order.order.model.OrderStatus.CREATED;
import static com.ecommerce.order.order.model.OrderStatus.PAID;
import static java.math.BigDecimal.ZERO;
import static java.time.Instant.now;


@Getter
@Builder
public class Order extends BaseAggregate {
    private String id;
    private List<OrderItem> items;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Address address;
    private Instant createdAt;

    public static Order create(String id, List<OrderItem> items, Address address) {
        Order order = Order.builder()
                .id(id)
                .items(items)
                .totalPrice(calculateTotalPrice(items))
                .status(CREATED)
                .address(address)
                .createdAt(now())
                .build();
        order.raiseCreatedEvent(id, items, address);
        return order;
    }

    private static BigDecimal calculateTotalPrice(List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(ZERO, BigDecimal::add);
    }

    private void raiseCreatedEvent(String id, List<OrderItem> items, Address address) {
        List<com.ecommerce.order.sdk.event.order.OrderItem> orderItems = items.stream()
                .map(orderItem -> new com.ecommerce.order.sdk.event.order.OrderItem(orderItem.getProductId(),
                        orderItem.getCount())).collect(Collectors.toList());
        raiseEvent(new OrderCreatedEvent(id, totalPrice, address, orderItems, createdAt));
    }

    public void changeProductCount(String productId, int count) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

        OrderItem orderItem = retrieveItem(productId);
        int originalCount = orderItem.getCount();
        orderItem.updateCount(count);
        this.totalPrice = calculateTotalPrice(items);
        raiseEvent(new OrderProductChangedEvent(id, productId, originalCount, count));
    }

    private OrderItem retrieveItem(String productId) {
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
        raiseEvent(new OrderPaidEvent(this.getId()));
    }

    public void changeAddressDetail(String detail) {
        if (this.status == PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

        this.address = this.address.changeDetailTo(detail);
        raiseEvent(new OrderAddressChangedEvent(getId(), detail, address.getDetail()));
    }

    public OrderRepresentation toRepresentation() {
        List<com.ecommerce.order.sdk.representation.order.OrderItem> itemRepresentations = this.getItems().stream()
                .map(orderItem -> new com.ecommerce.order.sdk.representation.order.OrderItem(orderItem.getProductId(),
                        orderItem.getCount(),
                        orderItem.getItemPrice()))
                .collect(Collectors.toList());

        return new OrderRepresentation(this.getId(),
                itemRepresentations,
                this.getTotalPrice(),
                this.getStatus().name(),
                this.getAddress(),
                this.getCreatedAt());
    }


    public OrderSummaryRepresentation toSummary() {
        return new OrderSummaryRepresentation(this.getId(),
                this.getTotalPrice(),
                this.getStatus().name(),
                this.getCreatedAt(),
                this.getAddress());
    }

}
