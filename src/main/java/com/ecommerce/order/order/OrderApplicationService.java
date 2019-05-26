package com.ecommerce.order.order;

import com.ecommerce.order.order.command.ChangeProductCountCommand;
import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.model.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.order.order.model.OrderId.orderId;
import static com.ecommerce.order.order.model.ProductId.productId;

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
    public OrderId createOrder(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(productId(item.getProductId()),
                        item.getCount(),
                        item.getItemPrice()))
                .collect(Collectors.toList());

        Order order = orderFactory.create(items, command.getAddress());
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void changeProductCount(String id, ChangeProductCountCommand command) {
        Order order = orderRepository.byId(orderId(id));
        order.changeProductCount(ProductId.productId(command.getProductId()), command.getCount());
        orderRepository.save(order);
    }

    @Transactional
    public void pay(String id, PayOrderCommand command) {
        Order order = orderRepository.byId(orderId(id));
        orderPaymentService.pay(order, command.getPaidPrice());
        orderRepository.save(order);
    }

    @Transactional
    public void changeAddressDetail(String id, String detail) {
        Order order = orderRepository.byId(orderId(id));
        order.changeAddressDetail(detail);
        orderRepository.save(order);
    }
}
