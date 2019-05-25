package com.ecommerce.order.product;

import com.ecommerce.order.common.event.DomainEvent;
import com.ecommerce.order.common.event.DomainEventType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public abstract class ProductEvent extends DomainEvent {
    private final ProductId productId;

    @JsonCreator
    protected ProductEvent(@JsonProperty("productId") ProductId productId,
                           @JsonProperty("_id") String _id,
                           @JsonProperty("_type") DomainEventType _type,
                           @JsonProperty("_createdAt") Instant _createdAt) {
        super(_id, _type, _createdAt);
        this.productId = productId;
    }

    public ProductEvent(ProductId productId, DomainEventType eventType) {
        super(eventType);
        this.productId = productId;
    }

    public ProductId getProductId() {
        return productId;
    }
}
