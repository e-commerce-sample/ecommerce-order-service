package com.ecommerce.order.order;

import com.ecommerce.order.order.model.OrderId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderPaymentProxy {
    public void pay(OrderId orderId, BigDecimal paidPrice) {
        //call remote payment service
    }
}
