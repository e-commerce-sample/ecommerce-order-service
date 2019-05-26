package com.ecommerce.common.event.order;

import com.ecommerce.common.event.DomainEventType;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.ecommerce.common.event.DomainEventType.ORDER_PRODUCT_CHANGED;

public class OrderProductChangedEvent extends OrderEvent {
    private final List<OrderItem> items;
    private final BigDecimal totalPrice;

    @JsonCreator
    public OrderProductChangedEvent(@JsonProperty("_id") String _id,
                                    @JsonProperty("_type") DomainEventType _type,
                                    @JsonProperty("_createdAt") Instant _createdAt,
                                    @JsonProperty("orderId") OrderId orderId,
                                    @JsonProperty("items") List<OrderItem> items,
                                    @JsonProperty("totalPrice") BigDecimal totalPrice) {
        super(orderId, _id, _type, _createdAt);
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public OrderProductChangedEvent(Order order) {
        super(order.getId(), ORDER_PRODUCT_CHANGED);
        this.items = order.getItems();
        this.totalPrice = order.getTotalPrice();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
