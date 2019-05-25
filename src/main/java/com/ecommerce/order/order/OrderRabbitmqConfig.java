package com.ecommerce.order.order;

import com.google.common.collect.ImmutableMap;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.google.common.collect.ImmutableMap.of;
import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
public class OrderRabbitmqConfig {

    public static final String ORDER_PUBLISH_EXCHANGE = "order-publish-x";
    public static final String ORDER_PUBLISH_DLX = "order-publish-dlx";
    public static final String ORDER_PUBLISH_DLQ = "order-publish-dlq";

    public static final String ORDER_MANUAL_DLQ_RETRY_EXCHANGE = "order-manual-dlq-retry-x";
    public static final String ORDER_RECEIVE_QUEUE = "order-receive-q";
    public static final String ORDER_RECEIVE_DLX = "order-receive-dlx";
    public static final String ORDER_RECEIVE_DLQ = "order-receive-dlq";


    //order上下文的"发送方exchange"，所有发自order上下文的消息都发自该exchange
    @Bean
    public TopicExchange orderPublishExchange() {
        return new TopicExchange(ORDER_PUBLISH_EXCHANGE, true, false, of("alternate-exchange", ORDER_PUBLISH_DLX));
    }

    //order上下文的"发送方DLX"，消息发送失败时传到该DLX
    @Bean
    public TopicExchange orderPublishDLX() {
        return new TopicExchange(ORDER_PUBLISH_DLX, true, false, null);
    }

    //order上下文的"发送方DLQ"，所有发到"发送DLX"的消息都将路由到该DLQ
    @Bean
    public Queue orderPublishDLQ() {
        return new Queue(ORDER_PUBLISH_DLQ, true, false, false, of("x-queue-mode", "lazy"));
    }

    //将order上下文的"发送方DLQ"绑定到"发送方DLX"
    @Bean
    public Binding publishDlqBinding() {
        return BindingBuilder.bind(orderPublishDLQ()).to(orderPublishDLX()).with("#");
    }

    //order上下文中接收到的所有消息都发送到该"接收方queue"
    @Bean
    public Queue orderReceiveQueue() {
        ImmutableMap<String, Object> args = of(
                "x-dead-letter-exchange",
                ORDER_RECEIVE_DLX,
                "x-overflow",
                "drop-head",
                "x-max-length",
                300000,
                "x-message-ttl",
                24 * 60 * 60 * 1000);
        return new Queue(ORDER_RECEIVE_QUEUE, true, false, false, args);
    }

    //order上下文的"手动重试exchange"，用于手动将"接收方DLQ"中的消息发自该DLX进行重试
    @Bean
    public TopicExchange orderManualRetryExchange() {
        return new TopicExchange(ORDER_MANUAL_DLQ_RETRY_EXCHANGE, true, false, of("alternate-exchange", ORDER_RECEIVE_DLX));
    }

    //将order上下文的"接收方Queue"绑定到"手动重试exchange"
    @Bean
    public Binding recoverBinding() {
        return BindingBuilder.bind(orderReceiveQueue()).to(orderManualRetryExchange()).with("#");
    }


    //order上下文的"接收方DLX"，消息接收失败时传到该DLX
    @Bean
    public TopicExchange orderReceiveDLX() {
        return new TopicExchange(ORDER_RECEIVE_DLX, true, false, null);
    }


    //order上下文的"接收方DLQ"，所有发到"接收DLX"的消息都将路由到该DLQ
    @Bean
    public Queue orderReceiveDLQ() {
        return new Queue(ORDER_RECEIVE_DLQ, true, false, false, of("x-queue-mode", "lazy"));
    }

    //将order上下文的"接收方DLQ"绑定到"接收方DLX"
    @Bean
    public Binding receiveDlqBinding() {
        return BindingBuilder.bind(orderReceiveDLQ()).to(orderReceiveDLX()).with("#");
    }

    //将order上下文的"接收方queue"绑定到自身的"发送方exchange"，用于CQRS异步更新OrderSummary
    @Bean
    public Binding orderBinding() {
        return new Binding(ORDER_RECEIVE_QUEUE, QUEUE, ORDER_PUBLISH_EXCHANGE, "order.#", null);
    }


}
