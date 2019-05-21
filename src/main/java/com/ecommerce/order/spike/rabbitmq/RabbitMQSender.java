package com.ecommerce.order.spike.rabbitmq;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import com.google.common.collect.ImmutableMap;
import com.rabbitmq.client.*;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.google.common.collect.ImmutableMap.of;
import static com.rabbitmq.client.BuiltinExchangeType.TOPIC;

public class RabbitMQSender {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("rabbitmq-user");
        factory.setPassword("rabbitmq-password");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        try (Connection conn = factory.newConnection(); Channel channel = conn.createChannel();) {
            //设置死信交换,Topic类型，持久化
            channel.exchangeDeclare("dlx", TOPIC, true, false, null);

            //设置死信队列，持久化，lazy型
            channel.queueDeclare("dlq", true, false, false, of("x-queue-mode", "lazy"));

            //接收所有发给dlx的消息，另外可以定义其他queue接收指定类型的消息
            channel.queueBind("dlq", "dlx", "#");


            //定义与order相关的事件exchange，如果无法路由，则路由到死信交换dlx
            channel.exchangeDeclare("order", TOPIC, true, false, of("alternate-exchange", "dlx"));


            //定义用于异步更新order读模型的queue，设置死信交换为dlx，队列满(x-overflow)时将头部消息发到dlx
            //定义queue的最大消息数(x-max-length)为300，满后发到dlx，另外定义消息的存活时间(x-message-ttl)为1天，1天后发送到dlx
            ImmutableMap<String, Object> orderSummaryQueueArguments = of("x-dead-letter-exchange",
                    "dlx",
                    "x-overflow",
                    "drop-head",
                    "x-max-length",
                    300,
                    "x-message-ttl",
                    24 * 60 * 60 * 1000);
            channel.queueDeclare("order-summary-queue", true, false, false, orderSummaryQueueArguments);
            channel.queueBind("order-summary-queue", "order", "order.#");


            //定义用于order创建时向用户发出通知的queue，设置死信交换为dlx
            ImmutableMap<String, Object> orderNotificationQueueArguments = of("x-dead-letter-exchange",
                    "dlx",
                    "x-overflow",
                    "drop-head",
                    "x-max-length",
                    300,
                    "x-message-ttl",
                    24 * 60 * 60 * 1000);
            channel.queueDeclare("order-notification-queue", true, false, false, orderNotificationQueueArguments);
            channel.queueBind("order-notification-queue", "order", "order.created");


            //设置发送端确认
            channel.addConfirmListener(new ConfirmListener() {
                public void handleAck(long seqNo, boolean multiple) {
                    if (multiple) {
                        logger.info(seqNo + "号及其以前的所有消息发送成功，当消息发送成功后执行相应逻辑，比如标记事件为已发送或者删除原来事件");
                    } else {
                        logger.info(seqNo + "号发送成功，当消息发送成功后执行相应逻辑，比如标记事件为已发送或者删除原来事件");

                    }
                }

                public void handleNack(long seqNo, boolean multiple) {
                    if (multiple) {
                        logger.info(seqNo + "号及其以前的所有消息发送失败，当消息发送失败后执行相应逻辑，比如重试或者标记事件发送失败");
                    } else {
                        logger.info(seqNo + "号发送失败，当消息发送失败后执行相应逻辑，比如重试或者标记事件发送失败");

                    }
                }
            });

            //开启发送者确认
            channel.confirmSelect();

            //设置消息持久化
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .contentType("application/json")
                    .deliveryMode(2)
                    .priority(0)
                    .build();


            //发送时没有必要设置mandatory，因为无法路由的消息会记录在dlq中
            //达到queue的上限时，queue头部消息将被放入dlx中
            try {
                channel.basicPublish("order", "order.created", false, properties, "create order data".getBytes());
                channel.basicPublish("order", "order.updated", false, properties, "update order data".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(5000);
        }

    }
}
