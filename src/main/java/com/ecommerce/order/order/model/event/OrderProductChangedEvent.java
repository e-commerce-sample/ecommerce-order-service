package com.ecommerce.order.order.model.event;

import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.model.OrderItem;
import com.ecommerce.order.common.event.DomainEvent;
import com.ecommerce.order.common.event.EventType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.ecommerce.order.common.event.EventType.ORDER_PRODUCT_CHANGED;

public class OrderProductChangedEvent extends DomainEvent {
    private final OrderId orderId;
    private final List<OrderItem> items;
    private final BigDecimal totalPrice;

    @JsonCreator
    public OrderProductChangedEvent(@JsonProperty("_id") String _id,
                                    @JsonProperty("_type") EventType _type,
                                    @JsonProperty("_createdAt") Instant _createdAt,
                                    @JsonProperty("orderId") OrderId orderId,
                                    @JsonProperty("items") List<OrderItem> items,
                                    @JsonProperty("totalPrice") BigDecimal totalPrice) {
        super(_id, _type, _createdAt);
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public OrderProductChangedEvent(Order order) {
        super(ORDER_PRODUCT_CHANGED);
        this.orderId = order.getId();
        this.items = order.getItems();
        this.totalPrice = order.getTotalPrice();
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
