package com.ecommerce.order.order.exception;

import com.ecommerce.shared.exception.AppException;

import static com.ecommerce.order.OrderErrorCode.PRODUCT_NOT_IN_ORDER;
import static com.google.common.collect.ImmutableMap.of;

public class ProductNotInOrderException extends AppException {
    public ProductNotInOrderException(String productId, String orderId) {
        super(PRODUCT_NOT_IN_ORDER, of("productId", productId,
                "orderId", orderId));
    }
}
