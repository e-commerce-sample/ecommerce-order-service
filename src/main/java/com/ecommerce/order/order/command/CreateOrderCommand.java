package com.ecommerce.order.order.command;

import com.ecommerce.order.common.ddd.Command;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CreateOrderCommand implements Command {
    @Valid
    @NotEmpty(message = "订单项不能为空.")
    private List<OrderItemCommand> items;

    @JsonCreator
    public CreateOrderCommand(@JsonProperty("items") List<OrderItemCommand> items) {
        this.items = items;
    }

    public List<OrderItemCommand> getItems() {
        return items;
    }
}
