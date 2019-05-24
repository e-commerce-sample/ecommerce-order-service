package com.ecommerce.order.product;

import com.ecommerce.order.common.event.DomainEventAwareAggregate;

import java.math.BigDecimal;
import java.time.Instant;

public class Product extends DomainEventAwareAggregate {
    private ProductId id;
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;

    private Product() {
    }

    private Product(String name, String description, BigDecimal price) {
        this.id = ProductId.newProductId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = Instant.now();
        raiseEvent(new ProductCreatedEvent(this));
    }

    public static Product create(String name, String description, BigDecimal price) {
        return new Product(name, description, price);
    }

    public ProductId getId() {
        return id;
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
