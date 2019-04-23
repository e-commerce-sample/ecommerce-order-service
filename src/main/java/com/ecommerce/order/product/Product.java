package com.ecommerce.order.product;

import com.ecommerce.order.common.ddd.AggregateRoot;

import java.math.BigDecimal;
import java.time.Instant;

public class Product implements AggregateRoot {
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
    }

    public static Product create(String name, String description, BigDecimal price) {
        return new Product(name, description, price);
    }

    public ProductId getId() {
        return id;
    }
}
