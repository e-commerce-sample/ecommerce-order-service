package com.ecommerce.order;

import com.ecommerce.common.event.CommonRabbitmqConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitmqConfig {

    @Autowired
    private CommonRabbitmqConfig commonRabbitmqConfig;


    //将order上下文的"接收方queue"绑定到自身的"发送方exchange"，用于CQRS异步更新OrderSummary
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(commonRabbitmqConfig.receiveQ()).to(commonRabbitmqConfig.publishExchange()).with("order.#");
    }


}
