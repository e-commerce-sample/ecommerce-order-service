package com.ecommerce.order.inventory;

import com.ecommerce.order.common.event.DomainEventAwareAggregate;
import com.ecommerce.order.product.ProductId;

import java.time.Instant;

public class Inventory extends DomainEventAwareAggregate {
    private ProductId productId;
    private String name;
    private int remains;
    private Instant createdAt;

    private Inventory() {
    }

    private Inventory(ProductId productId, String name) {
        this.productId = productId;
        this.name = name;
        this.remains = 100;
        this.createdAt = Instant.now();
    }

    public static Inventory create(ProductId productId, String name) {
        return new Inventory(productId, name);
    }

    public void decrease(int number) {
        this.remains = this.remains - number;
        raiseEvent(new InventoryChangedEvent(productId, remains));
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getRemains() {
        return remains;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
