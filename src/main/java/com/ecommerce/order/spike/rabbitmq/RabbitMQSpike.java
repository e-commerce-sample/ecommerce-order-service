package com.ecommerce.order.spike.rabbitmq;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import com.rabbitmq.client.*;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSpike {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("rabbitmq-user");
        factory.setPassword("rabbitmq-password");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        Connection conn = factory.newConnection();
        factory.setExceptionHandler(new ExceptionHandler() {
            @Override
            public void handleUnexpectedConnectionDriverException(Connection conn, Throwable exception) {
                logger.error("ddd", exception);
            }

            @Override
            public void handleReturnListenerException(Channel channel, Throwable exception) {
                logger.error("ddd1", exception);

            }

            @Override
            public void handleConfirmListenerException(Channel channel, Throwable exception) {
                logger.error("ddd31", exception);

            }

            @Override
            public void handleBlockedListenerException(Connection connection, Throwable exception) {
                logger.error("ddd61", exception);

            }

            @Override
            public void handleConsumerException(Channel channel, Throwable exception, Consumer consumer, String consumerTag, String methodName) {
                logger.error("ddd111", exception);

            }

            @Override
            public void handleConnectionRecoveryException(Connection conn, Throwable exception) {
                logger.error("ddd21", exception);

            }

            @Override
            public void handleChannelRecoveryException(Channel ch, Throwable exception) {
                logger.error("ddd41", exception);

            }

            @Override
            public void handleTopologyRecoveryException(Connection conn, Channel ch, TopologyRecoveryException exception) {
                logger.error("ddd31", exception);

            }
        });
                Channel channel = conn.createChannel();
                channel.exchangeDeclare("myexchange","direct");
        for (long i = 0; i < 10; ++i) {
                channel.addConfirmListener(new ConfirmListener() {
                    public void handleAck(long seqNo, boolean multiple) {
                        if (multiple) {
                            logger.info("mutilpal" + seqNo);
                        } else {
                            logger.info("non multipal" + seqNo);
                        }
                    }

                    public void handleNack(long seqNo, boolean multiple) {
                        // handle the lost messages somehow
                        logger.info("nack" + seqNo);
                    }
                });
                channel.addReturnListener(new ReturnListener() {
                    @Override
                    public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        logger.info("returned" + new String(body) + properties.getMessageId());
                    }
                });
                channel.confirmSelect();
                logger.info("send");
//            unconfirmedSet.add(channel.getNextPublishSeqNo());
                channel.basicPublish("myexchange", "myqueu", false, MessageProperties.PERSISTENT_BASIC,
                        "nop".getBytes());

            Thread.sleep(1000);
        }
        Thread.sleep(10000);
        conn.close();

    }
}
