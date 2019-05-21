package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.google.common.collect.ImmutableMap.of;

@Configuration
public class SpringRabbitConfig {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

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
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                logger.info("confirm " + ack + cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                logger.info("returned");
            }
        });
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }


    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitListenerErrorHandler rabbitListenerErrorHandler() {
        return new RabbitListenerErrorHandler() {
            @Override
            public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
                if (amqpMessage.getMessageProperties().isRedelivered()) {
                    throw new AmqpRejectAndDontRequeueException(exception);
                }
                throw new RuntimeException(exception);
            }
        };
    }
}
