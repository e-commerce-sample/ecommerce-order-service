package com.ecommerce.order.order;

import com.ecommerce.order.common.ddd.ApplicationService;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.representation.OrderRepresentation;
import com.ecommerce.order.order.representation.OrderRepresentationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.ecommerce.order.order.model.OrderId.orderId;

@Component
public class OrderApplicationService implements ApplicationService {
    private final OrderRepresentationService representationService;
    private final OrderRepository repository;

    public OrderApplicationService(OrderRepresentationService representationService,
                                   OrderRepository repository) {
        this.representationService = representationService;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public OrderRepresentation byId(String id) {
        Order order = repository.byId(orderId(id));
        return representationService.toRepresentation(order);
    }

}
