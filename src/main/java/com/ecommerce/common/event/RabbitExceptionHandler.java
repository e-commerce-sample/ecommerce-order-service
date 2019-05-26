package com.ecommerce.common.event;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;

public class RabbitExceptionHandler extends ConditionalRejectingErrorHandler {

    @Override
    public void handleError(Throwable t) {
        if (t instanceof ListenerExecutionFailedException) {
            ListenerExecutionFailedException exception = (ListenerExecutionFailedException) t;
            Message failedMessage = exception.getFailedMessage();
            if (failedMessage.getMessageProperties().isRedelivered()) {
                throw new AmqpRejectAndDontRequeueException(exception);
            }
            throw new RuntimeException(t);
        }

        throw new AmqpRejectAndDontRequeueException(t);
    }
}
