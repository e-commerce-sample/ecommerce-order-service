package com.ecommerce.order.order;

import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderItem;
import com.ecommerce.shared.model.Address;
import org.junit.jupiter.api.Test;

import static com.ecommerce.order.order.model.OrderItem.create;
import static com.ecommerce.order.order.model.OrderStatus.CREATED;
import static com.ecommerce.shared.utils.UuidGenerator.newUuid;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    public void should_create_order() {
        Address address = Address.of("四川", "成都", "天府软件园1号");

        OrderItem orderItem1 = create(newUuid(), 2, valueOf(20));
        OrderItem orderItem2 = create(newUuid(), 3, valueOf(30));
        Order order = Order.create(newUuid(), newArrayList(orderItem1, orderItem2), address);
        assertThat(valueOf(130), comparesEqualTo(order.getTotalPrice()));
        assertEquals(CREATED, order.getStatus());
    }

}