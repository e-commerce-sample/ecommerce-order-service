package com.ecommerce.order.order;

import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderFactory;
import com.ecommerce.order.order.model.OrderItem;
import com.ecommerce.order.sdk.command.order.ChangeProductCountCommand;
import com.ecommerce.order.sdk.command.order.CreateOrderCommand;
import com.ecommerce.order.sdk.command.order.PayOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final OrderPaymentService orderPaymentService;

    public OrderApplicationService(OrderRepository orderRepository,
                                   OrderFactory orderFactory,
                                   OrderPaymentService orderPaymentService) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderPaymentService = orderPaymentService;
    }

    @Transactional
    public String createOrder(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(item.getProductId(),
                        item.getCount(),
                        item.getItemPrice()))
                .collect(Collectors.toList());

        Order order = orderFactory.create(items, command.getAddress());
        orderRepository.save(order);
        log.info("Created order[{}].", order.getId());
        return order.getId();
    }

    @Transactional
    public void changeProductCount(String id, ChangeProductCountCommand command) {
        Order order = orderRepository.byId(id);
        order.changeProductCount(command.getProductId(), command.getCount());
        orderRepository.save(order);
    }

    @Transactional
    public void pay(String id, PayOrderCommand command) {
        Order order = orderRepository.byId(id);
        orderPaymentService.pay(order, command.getPaidPrice());
        orderRepository.save(order);
    }

    @Transactional
    public void changeAddressDetail(String id, String detail) {
        Order order = orderRepository.byId(id);
        order.changeAddressDetail(detail);
        orderRepository.save(order);
    }
}
