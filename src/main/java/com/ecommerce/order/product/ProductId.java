package com.ecommerce.order.product;

import com.ecommerce.order.common.ddd.Identity;

import java.util.Objects;

import static com.ecommerce.order.common.utils.UuidGenerator.newUuid;

public class ProductId implements Identity {
    private String id;

    private ProductId() {
    }

    private ProductId(String id) {
        this.id = id;
    }

    public static ProductId productId(String id) {
        return new ProductId(id);
    }

    public static ProductId newProductId() {
        return new ProductId(newUuid());
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductId productId = (ProductId) o;
        return Objects.equals(id, productId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
