package com.ecommerce.order.order;

import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderItem;
import org.junit.jupiter.api.Test;

import static com.ecommerce.order.common.utils.UuidGenerator.newUuid;
import static com.ecommerce.order.order.model.OrderId.orderId;
import static com.ecommerce.order.order.model.OrderItem.create;
import static com.ecommerce.order.order.model.OrderStatus.CREATED;
import static com.ecommerce.order.product.ProductId.newProductId;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    public void should_create_order() {
        OrderItem orderItem1 = create(newProductId(), 2, valueOf(20));
        OrderItem orderItem2 = create(newProductId(), 3, valueOf(30));
        Order order = Order.create(orderId(newUuid()), newArrayList(orderItem1, orderItem2));
        assertThat(valueOf(130), comparesEqualTo(order.getTotalPrice()));
        assertEquals(CREATED, order.getStatus());
    }

}