package com.ecommerce.order.sdk.command.order;

import com.ecommerce.shared.model.Address;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class CreateOrderCommand {
    @Valid
    @NotEmpty(message = "订单项不能为空")
    private List<OrderItemCommand> items;

    @NotNull(message = "订单地址不能为空")
    private Address address;

}
