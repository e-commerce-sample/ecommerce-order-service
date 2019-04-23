package com.ecommerce.order.product.representation;

import com.ecommerce.order.common.ddd.Representation;

import java.math.BigDecimal;

public class ProductSummaryRepresentation implements Representation {
    private String id;
    private String name;
    private BigDecimal price;

    public ProductSummaryRepresentation(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
