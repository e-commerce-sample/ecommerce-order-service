package com.ecommerce.order.spike.rabbitmq;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import com.rabbitmq.client.*;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class RabbitMQReceiver {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("rabbitmq-user");
        factory.setPassword("rabbitmq-password");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        channel.basicQos(1, true);

        boolean autoAck = false;
        channel.basicConsume("order-summary-queue", autoAck,
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag,
                                               Envelope envelope,
                                               AMQP.BasicProperties properties,
                                               byte[] body)
                            throws IOException {
                        long deliveryTag = envelope.getDeliveryTag();

                        //用Random来模拟有时处理成功有时处理失败的场景
                        if (new Random().nextBoolean()) {
                            logger.info("成功消费消息" + deliveryTag);
                            channel.basicAck(deliveryTag, false);
                        } else {
                            if (envelope.isRedeliver()) {
                                logger.warn("首次消费消息" + deliveryTag + "不成功，尝试重试");
                                channel.basicNack(deliveryTag, false, false);
                            } else {
                                logger.warn("第二次消费消息" + deliveryTag + "不成功，扔到DLX");
                                channel.basicNack(deliveryTag, false, true);
                            }
                        }
                    }
                });
    }
}
