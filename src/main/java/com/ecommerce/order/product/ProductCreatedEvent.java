package com.ecommerce.order.product;

import com.ecommerce.order.common.event.DomainEvent;
import com.ecommerce.order.common.event.DomainEventType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

import static com.ecommerce.order.common.event.DomainEventType.PRODUCT_CREATED;

public class ProductCreatedEvent extends DomainEvent {
    private final ProductId productId;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Instant createdAt;

    @JsonCreator
    private ProductCreatedEvent(@JsonProperty("_id") String _id,
                                @JsonProperty("_type") DomainEventType _type,
                                @JsonProperty("_createdAt") Instant _createdAt,
                                @JsonProperty("productId") ProductId productId,
                                @JsonProperty("name") String name,
                                @JsonProperty("description") String description,
                                @JsonProperty("price") BigDecimal price,
                                @JsonProperty("createdAt") Instant createdAt) {
        super(_id, _type, _createdAt);
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
    }

    public ProductCreatedEvent(Product product) {
        super(PRODUCT_CREATED);
        this.productId = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
