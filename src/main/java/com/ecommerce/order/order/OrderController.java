package com.ecommerce.order.order;

import com.ecommerce.order.common.utils.PagedResource;
import com.ecommerce.order.order.command.ChangeAddressDetailCommand;
import com.ecommerce.order.order.command.ChangeProductCountCommand;
import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.order.representation.detail.OrderRepresentation;
import com.ecommerce.order.order.representation.OrderRepresentationService;
import com.ecommerce.order.order.representation.summary.OrderSummaryRepresentation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderApplicationService applicationService;
    private final OrderRepresentationService representationService;

    public OrderController(OrderApplicationService applicationService,
                           OrderRepresentationService representationService) {
        this.applicationService = applicationService;
        this.representationService = representationService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrderId createOrder(@RequestBody @Valid CreateOrderCommand command) {
        return applicationService.createOrder(command);
    }

    @PostMapping("/{id}/products")
    public void changeProductCount(@PathVariable(name = "id") String id, @RequestBody @Valid ChangeProductCountCommand command) {
        applicationService.changeProductCount(id, command);
    }

    @PostMapping("/{id}/payment")
    public void pay(@PathVariable(name = "id") String id, @RequestBody @Valid PayOrderCommand command) {
        applicationService.pay(id, command);
    }


    @PostMapping("/{id}/address/detail")
    public void changeAddressDetail(@PathVariable(name = "id") String id, @RequestBody @Valid ChangeAddressDetailCommand command) {
        applicationService.changeAddressDetail(id, command.getDetail());
    }

    @GetMapping("/{id}")
    public OrderRepresentation byId(@PathVariable(name = "id") String id) {
        return representationService.byId(id);
    }

    @GetMapping
    public PagedResource<OrderSummaryRepresentation> pagedProducts(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                                                   @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return representationService.listOrders(pageIndex, pageSize);
    }


}
