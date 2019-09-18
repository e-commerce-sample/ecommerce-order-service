package com.ecommerce.order;

import com.ecommerce.spring.common.event.messaging.rabbit.EcommerceRabbitProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
public class OrderRabbitmqConfig {


    private EcommerceRabbitProperties properties;

    public OrderRabbitmqConfig(EcommerceRabbitProperties properties) {
        this.properties = properties;
    }


    //将order上下文的"接收方queue"绑定到自身的"发送方exchange"，用于CQRS异步更新OrderSummary
    @Bean
    public Binding bindToInventoryChanged() {
        return new Binding(properties.getReceiveQ(), QUEUE, "order-publish-x", "com.ecommerce.order.sdk.event.order.#", null);
    }


}
