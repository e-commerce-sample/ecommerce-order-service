package com.ecommerce.order.order;

import com.ecommerce.order.BaseComponentTest;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.shared.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static com.ecommerce.order.order.model.OrderItem.create;
import static com.ecommerce.shared.utils.UuidGenerator.newUuid;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderRepositoryComponentTest extends BaseComponentTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void should_save_order() {
        Address address = Address.of("四川", "成都", "天府软件园1号");
        Order order = Order.create(newUuid(), newArrayList(create(newUuid(), 2, valueOf(20))), address);
        orderRepository.save(order);
        Order saved = orderRepository.byId(order.getId());
        assertEquals(order.getCreatedAt(), saved.getCreatedAt());
        assertEquals(address, order.getAddress());
        assertEquals(BigDecimal.valueOf(40), order.getTotalPrice());
        assertEquals(1, order.getItems().size());
    }

}