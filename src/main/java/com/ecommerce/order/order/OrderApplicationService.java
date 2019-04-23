package com.ecommerce.order.order;

import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderFactory;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.model.OrderItem;
import com.ecommerce.order.order.representation.OrderRepresentation;
import com.ecommerce.order.order.representation.OrderRepresentationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.order.order.model.OrderId.orderId;
import static com.ecommerce.order.product.ProductId.productId;

@Component
public class OrderApplicationService {
    private final OrderRepresentationService representationService;
    private final OrderRepository repository;
    private final OrderFactory factory;

    public OrderApplicationService(OrderRepresentationService representationService,
                                   OrderRepository repository,
                                   OrderFactory factory) {
        this.representationService = representationService;
        this.repository = repository;
        this.factory = factory;
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

}
