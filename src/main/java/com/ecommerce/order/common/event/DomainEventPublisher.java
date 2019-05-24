package com.ecommerce.order.common.event;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DomainEventPublisher {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();
    private static final String BOUNDED_CONTEXT = "order";
    private final DomainEventRepository repository;
    private final RabbitTemplate rabbitTemplate;


    public DomainEventPublisher(DomainEventRepository repository,
                                ConnectionFactory connectionFactory,
                                MessageConverter messageConverter) {
        this.repository = repository;
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause)
                -> this.repository.delete(correlationData.getId()));
        this.rabbitTemplate = rabbitTemplate;
    }


    @Scheduled(fixedDelay = 2000)
    @SchedulerLock(name = "domain-event-publisher", lockAtMostFor = 30000, lockAtLeastFor = 1000)
    public void run() {
        List<DomainEvent> domainEvents = repository.newestEvents();
        domainEvents.forEach(domainEvent -> {
            try {
                EventType type = domainEvent.get_type();
                String exchange = BOUNDED_CONTEXT + "." + type.getAggregate();
                String routingKey = type.getAggregate() + "." + type.getType();
                rabbitTemplate.convertAndSend(exchange, routingKey, domainEvent, new CorrelationData(domainEvent.get_id()));
            } catch (Throwable t) {
                logger.error("Error while publish {}:{}", domainEvent, t.getMessage());
            }
        });
    }

}
