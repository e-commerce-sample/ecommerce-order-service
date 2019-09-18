package com.ecommerce.order.order;

import com.ecommerce.order.order.representation.OrderRepresentationService;
import com.ecommerce.order.sdk.command.order.ChangeAddressDetailCommand;
import com.ecommerce.order.sdk.command.order.ChangeProductCountCommand;
import com.ecommerce.order.sdk.command.order.CreateOrderCommand;
import com.ecommerce.order.sdk.command.order.PayOrderCommand;
import com.ecommerce.order.sdk.representation.order.OrderRepresentation;
import com.ecommerce.order.sdk.representation.order.OrderSummaryRepresentation;
import com.ecommerce.shared.utils.PagedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
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
    public Map<String, String> createOrder(@RequestBody @Valid CreateOrderCommand command) {
        return of("id", applicationService.createOrder(command));
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
