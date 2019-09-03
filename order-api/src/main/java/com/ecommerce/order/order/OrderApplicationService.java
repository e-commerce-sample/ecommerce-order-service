package com.ecommerce.order.order;

import com.ecommerce.order.order.command.ChangeProductCountCommand;
import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderFactory;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.model.OrderItem;
import com.ecommerce.order.order.model.ProductId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.order.order.model.OrderId.of;
import static com.ecommerce.order.order.model.ProductId.productId;

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
    public OrderId createOrder(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(productId(item.getProductId()),
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
        Order order = orderRepository.byId(of(id));
        order.changeProductCount(ProductId.productId(command.getProductId()), command.getCount());
        orderRepository.save(order);
    }

    @Transactional
    public void pay(String id, PayOrderCommand command) {
        Order order = orderRepository.byId(of(id));
        orderPaymentService.pay(order, command.getPaidPrice());
        orderRepository.save(order);
    }

    @Transactional
    public void changeAddressDetail(String id, String detail) {
        Order order = orderRepository.byId(of(id));
        order.changeAddressDetail(detail);
        orderRepository.save(order);
    }
}
