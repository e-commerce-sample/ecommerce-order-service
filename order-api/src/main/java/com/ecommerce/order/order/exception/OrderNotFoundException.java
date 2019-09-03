package com.ecommerce.order.order.exception;

import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.shared.exception.AppException;

import static com.ecommerce.order.OrderErrorCode.ORDER_NOT_FOUND;
import static com.google.common.collect.ImmutableMap.of;

public class OrderNotFoundException extends AppException {
    public OrderNotFoundException(OrderId orderId) {
        super(ORDER_NOT_FOUND, of("orderId", orderId.toString()));
    }
}
