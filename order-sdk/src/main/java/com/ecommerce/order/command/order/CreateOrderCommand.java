package com.ecommerce.order.command.order;

import com.ecommerce.shared.model.Address;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateOrderCommand {
    @Valid
    @NotEmpty(message = "订单项不能为空")
    private List<OrderItemCommand> items;

    @NotNull(message = "订单地址不能为空")
    private Address address;

    @JsonCreator
    public CreateOrderCommand(@JsonProperty("items") List<OrderItemCommand> items,
                              @JsonProperty("address") Address address) {
        this.items = items;
        this.address = address;
    }

    public List<OrderItemCommand> getItems() {
        return items;
    }

    public Address getAddress() {
        return address;
    }
}
