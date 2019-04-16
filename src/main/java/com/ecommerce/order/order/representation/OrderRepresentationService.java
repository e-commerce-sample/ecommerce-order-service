package com.ecommerce.order.order.representation;

import com.ecommerce.order.order.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderRepresentationService {

    public OrderRepresentation toRepresentation(Order order) {
        List<OrderItemRepresentation> itemRepresentations = order.getItems().stream()
                .map(orderItem -> new OrderItemRepresentation(orderItem.getProductId().toString(),
                        orderItem.getCount(),
                        orderItem.getItemPrice()))
                .collect(Collectors.toList());

        return new OrderRepresentation(order.getId().toString(),
                itemRepresentations,
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt());
    }
}
