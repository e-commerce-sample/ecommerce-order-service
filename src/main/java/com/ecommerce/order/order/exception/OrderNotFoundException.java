package com.ecommerce.order.order.exception;

import com.ecommerce.order.common.exception.AppException;
import com.ecommerce.order.order.model.OrderId;
import com.google.common.collect.ImmutableMap;

import static com.ecommerce.order.common.exception.ErrorCode.ORDER_NOT_FOUND;

public class OrderNotFoundException extends AppException {
    public OrderNotFoundException(OrderId orderId) {
        super(ORDER_NOT_FOUND, ImmutableMap.of("orderId", orderId.toString()));
    }
}
