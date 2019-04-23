package com.ecommerce.order.order;

import com.ecommerce.order.common.ddd.ApplicationService;
import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.command.UpdateProductCountCommand;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderFactory;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.model.OrderItem;
import com.ecommerce.order.order.representation.OrderRepresentation;
import com.ecommerce.order.order.representation.OrderRepresentationService;
import com.ecommerce.order.product.ProductId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.order.order.model.OrderId.orderId;
import static com.ecommerce.order.product.ProductId.productId;

@Component
public class OrderApplicationService implements ApplicationService {
    private final OrderRepresentationService representationService;
    private final OrderRepository repository;
    private final OrderFactory factory;
    private final OrderPaymentService paymentService;

    public OrderApplicationService(OrderRepresentationService representationService,
                                   OrderRepository repository,
                                   OrderFactory factory,
                                   OrderPaymentService paymentService) {
        this.representationService = representationService;
        this.repository = repository;
        this.factory = factory;
        this.paymentService = paymentService;
    }

    @Transactional
    public OrderId createOrder(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(productId(item.getProductId()),
                        item.getCount(),
                        item.getItemPrice()))
                .collect(Collectors.toList());

        Order order = factory.create(items);
        repository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true)
    public OrderRepresentation byId(String id) {
        Order order = repository.byId(orderId(id));
        return representationService.toRepresentation(order);
    }

    @Transactional
    public void updateProductCount(String id, UpdateProductCountCommand command) {
        Order order = repository.byId(orderId(id));
        order.updateProductCount(ProductId.productId(command.getProductId()), command.getCount());
        repository.save(order);
    }

    @Transactional
    public void pay(String id, PayOrderCommand command) {
        Order order = repository.byId(orderId(id));
        paymentService.pay(order, command.getPaidPrice());
        repository.save(order);
    }
}
