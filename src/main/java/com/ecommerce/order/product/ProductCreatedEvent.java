package com.ecommerce.order.product;

import com.ecommerce.order.common.event.DomainEventType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

import static com.ecommerce.order.common.event.DomainEventType.PRODUCT_CREATED;

public class ProductCreatedEvent extends ProductEvent {
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
        super(productId, _id, _type, _createdAt);
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
    }

    public ProductCreatedEvent(Product product) {
        super(product.getId(), PRODUCT_CREATED);
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
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
