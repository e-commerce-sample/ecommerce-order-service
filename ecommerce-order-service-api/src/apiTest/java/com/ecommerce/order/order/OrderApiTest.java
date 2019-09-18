package com.ecommerce.order.order;

import com.ecommerce.order.BaseApiTest;
import com.ecommerce.order.order.model.Order;
import com.ecommerce.order.sdk.command.order.ChangeAddressDetailCommand;
import com.ecommerce.order.sdk.command.order.ChangeProductCountCommand;
import com.ecommerce.order.sdk.command.order.CreateOrderCommand;
import com.ecommerce.order.sdk.command.order.OrderItemCommand;
import com.ecommerce.order.sdk.command.order.PayOrderCommand;
import com.ecommerce.shared.event.DomainEventPublisher;
import com.ecommerce.shared.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static com.ecommerce.order.order.model.OrderItem.create;
import static com.ecommerce.order.order.model.OrderStatus.PAID;
import static com.ecommerce.shared.utils.UuidGenerator.newUuid;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.valueOf;
import static java.util.stream.IntStream.range;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderApiTest extends BaseApiTest {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    @Test
    public void should_create_order() {
        Address address = Address.of("四川", "成都", "天府软件园1号");
        String id = given()
                .contentType("application/json")
                .body(new CreateOrderCommand(newArrayList(new OrderItemCommand("12345", 2, BigDecimal.valueOf(20))), address))
                .when()
                .post("/orders")
                .then().statusCode(201)
                .extract().body().jsonPath().getString("id");
        Order order = repository.byId(id);
        assertNotNull(order);
    }

    @Test
    public void should_retrieve_order() {
        Address address = Address.of("四川", "成都", "天府软件园1号");
        Order order = Order.create(newUuid(), newArrayList(create(newUuid(), 20, BigDecimal.valueOf(30))), address);
        repository.save(order);
        String idString = order.getId();
        given()
                .when()
                .get("/orders/{id}", idString)
                .then().statusCode(200)
                .body("id", is(idString));
    }

    @Test
    public void should_list_order_summary() throws InterruptedException {
        range(0, 10).forEach(value -> {
            Address address = Address.of("四川", "成都", "天府软件园1号");
            Order order = Order.create(newUuid(), newArrayList(create(newUuid(), 20, BigDecimal.valueOf(30))), address);
            repository.save(order);
        });
        domainEventPublisher.publishNextBatch();
        Thread.sleep(3000);

        given()
                .when()
                .get("/orders?pageIndex=1&pageSize=5")
                .then().statusCode(200)
                .body("pageIndex", is(1))
                .body("resource.size()", is(5));
    }


    @Test
    public void should_update_product_count() {
        Address address = Address.of("四川", "成都", "天府软件园1号");

        String productId = newUuid();
        Order order = Order.create(newUuid(), newArrayList(create(productId, 20, BigDecimal.valueOf(30))), address);
        repository.save(order);
        String idString = order.getId();

        given().contentType("application/json")
                .body(new ChangeProductCountCommand(productId, 30))
                .when()
                .post("orders/{id}/products", idString)
                .then().statusCode(200);

        Order saved = repository.byId(order.getId());
        assertEquals(30, saved.getItems().get(0).getCount());
        assertThat(valueOf(900), comparesEqualTo(saved.getTotalPrice()));

    }


    @Test
    public void should_pay_order() {
        Address address = Address.of("四川", "成都", "天府软件园1号");
        String productId = newUuid();
        Order order = Order.create(newUuid(), newArrayList(create(productId, 20, BigDecimal.valueOf(30))), address);
        repository.save(order);
        String idString = order.getId();

        given().contentType("application/json")
                .body(new PayOrderCommand(BigDecimal.valueOf(600)))
                .when()
                .post("orders/{id}/payment", idString)
                .then().statusCode(200);

        Order saved = repository.byId(order.getId());
        assertEquals(PAID, saved.getStatus());

    }


    @Test
    public void should_change_order_address_detail() {
        Address address = Address.of("四川", "成都", "天府软件园1号");
        String productId = newUuid();
        Order order = Order.create(newUuid(), newArrayList(create(productId, 20, BigDecimal.valueOf(30))), address);
        repository.save(order);
        String idString = order.getId();

        given().contentType("application/json")
                .body(new ChangeAddressDetailCommand("天府软件园2号"))
                .when()
                .post("orders/{id}/address/detail", idString)
                .then().statusCode(200);

        Order saved = repository.byId(order.getId());
        Address expected = Address.of("四川", "成都", "天府软件园2号");
        assertEquals(expected, saved.getAddress());

    }

}