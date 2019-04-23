package com.ecommerce.order.product;

import com.ecommerce.order.common.exception.AppException;
import com.google.common.collect.ImmutableMap;

import static com.ecommerce.order.common.exception.ErrorCode.PRODUCT_NOT_FOUND;

public class ProductNotFoundException extends AppException {
    public ProductNotFoundException(ProductId id) {
        super(PRODUCT_NOT_FOUND, ImmutableMap.of("productId", id.toString()));
    }
}
