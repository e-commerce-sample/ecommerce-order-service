package com.ecommerce.order.order;

import com.ecommerce.order.BaseApiTest;
import com.ecommerce.order.order.command.CreateOrderCommand;
import com.ecommerce.order.order.command.OrderItemCommand;
import com.ecommerce.order.order.command.PayOrderCommand;
import com.ecommerce.order.order.command.UpdateProductCountCommand;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.order.model.OrderId;
import com.ecommerce.order.product.ProductId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static com.ecommerce.order.common.utils.UuidGenerator.newUuid;
import static com.ecommerce.order.order.model.OrderId.orderId;
import static com.ecommerce.order.order.model.OrderItem.create;
import static com.ecommerce.order.order.model.OrderStatus.PAID;
import static com.ecommerce.order.product.ProductId.newProductId;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderApiTest extends BaseApiTest {

    @Autowired
    private OrderRepository repository;

    @Test
    public void should_create_order() {
        String id = given()
                .contentType("application/json")
                .body(new CreateOrderCommand(newArrayList(new OrderItemCommand("12345", 2, BigDecimal.valueOf(20)))))
                .when()
                .post("/orders")
                .then().statusCode(201)
                .extract().body().jsonPath().getString("id");
        Order order = repository.byId(OrderId.orderId(id));
        assertNotNull(order);
    }

    @Test
    public void should_retrieve_order() {
        Order order = Order.create(orderId(newUuid()), newArrayList(create(newProductId(), 20, BigDecimal.valueOf(30))));
        repository.save(order);
        String idString = order.getId().toString();
        given()
                .when()
                .get("/orders/{id}", idString)
                .then().statusCode(200)
                .body("id", is(idString));
    }

    @Test
    public void should_update_product_count() {
        ProductId productId = newProductId();
        Order order = Order.create(orderId(newUuid()), newArrayList(create(productId, 20, BigDecimal.valueOf(30))));
        repository.save(order);
        String idString = order.getId().toString();

        given().contentType("application/json")
                .body(new UpdateProductCountCommand(productId.toString(), 30))
                .when()
                .post("orders/{id}", idString)
                .then().statusCode(200);

        Order saved = repository.byId(order.getId());
        assertEquals(30, saved.getItems().get(0).getCount());
        assertThat(valueOf(900), comparesEqualTo(saved.getTotalPrice()));

    }


    @Test
    public void should_pay_order() {
        ProductId productId = newProductId();
        Order order = Order.create(orderId(newUuid()), newArrayList(create(productId, 20, BigDecimal.valueOf(30))));
        repository.save(order);
        String idString = order.getId().toString();

        given().contentType("application/json")
                .body(new PayOrderCommand(BigDecimal.valueOf(600)))
                .when()
                .post("orders/{id}/payment", idString)
                .then().statusCode(200);

        Order saved = repository.byId(order.getId());
        assertEquals(PAID, saved.getStatus());

    }

}