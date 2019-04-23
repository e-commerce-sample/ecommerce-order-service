package com.ecommerce.order.order;

import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.command.UpdateProductCountCommand;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.representation.OrderRepresentation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderApplicationService service;

    public OrderController(OrderApplicationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrderId createOrder(@RequestBody @Valid CreateOrderCommand command) {
        return service.createOrder(command);
    }

    @PostMapping("/{id}")
    public void updateProductCount(@PathVariable(name = "id") String id, @RequestBody @Valid UpdateProductCountCommand command) {
        service.updateProductCount(id, command);
    }

    @PostMapping("/{id}/payment")
    public void pay(@PathVariable(name = "id") String id, @RequestBody @Valid PayOrderCommand command) {
        service.pay(id, command);
    }

    @GetMapping("/{id}")
    public OrderRepresentation byId(@PathVariable(name = "id") String id) {
        return service.byId(id);
    }
}
