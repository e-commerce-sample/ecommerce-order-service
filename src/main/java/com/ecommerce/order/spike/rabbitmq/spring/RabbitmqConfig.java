package com.ecommerce.order.spike.rabbitmq.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.google.common.collect.ImmutableMap.of;

@Configuration
public class RabbitmqConfig {

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter(objectMapper);
        messageConverter.setClassMapper(classMapper());
        return messageConverter;
    }


    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("*");
        return classMapper;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(1);
        factory.setConcurrentConsumers(5);
        factory.setMaxConcurrentConsumers(20);
        factory.setErrorHandler(new RabbitExceptionHandler());
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public TopicExchange dlx() {
        return new TopicExchange("dlx", true, false, null);
    }

    @Bean
    public Queue dlq() {
        return new Queue("dlq", true, false, false, of("x-queue-mode", "lazy"));
    }

    @Bean
    public Binding DlqToDlxBinding() {
        return BindingBuilder.bind(dlq()).to(dlx()).with("#");
    }


    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange("order", true, false, of("alternate-exchange", "dlx"));
    }

    @Bean
    public Queue orderSummaryQueue() {
        ImmutableMap<String, Object> orderSummaryQueueArguments = of("x-dead-letter-exchange",
                "dlx",
                "x-overflow",
                "drop-head",
                "x-max-length",
                300,
                "x-message-ttl",
                24 * 60 * 60 * 1000);
        return new Queue("order-summary-queue", true, false, false, orderSummaryQueueArguments);
    }

    @Bean
    public Binding orderSummaryQueueBinding() {
        return BindingBuilder.bind(orderSummaryQueue()).to(orderExchange()).with("order.#");
    }

    @Bean
    public Queue orderNotificationQueue() {
        ImmutableMap<String, Object> orderNotificationQueueArguments = of("x-dead-letter-exchange",
                "dlx",
                "x-overflow",
                "drop-head",
                "x-max-length",
                300,
                "x-message-ttl",
                24 * 60 * 60 * 1000);
        return new Queue("order-notification-queue", true, false, false, orderNotificationQueueArguments);
    }

    @Bean
    public Binding orderNotificationQueueBinding() {
        return BindingBuilder.bind(orderNotificationQueue()).to(orderExchange()).with("order.created");
    }

    @Bean
    public Queue productNotificationQueue() {
        ImmutableMap<String, Object> args = of("x-dead-letter-exchange",
                "dlx",
                "x-overflow",
                "drop-head",
                "x-max-length",
                300,
                "x-message-ttl",
                24 * 60 * 60 * 1000);
        return new Queue("product-notification-queue", true, false, false, args);
    }

    @Bean
    public Binding productNotificationQueueBinding() {
        return BindingBuilder.bind(productNotificationQueue()).to(orderExchange()).with("product.created");
    }

}
