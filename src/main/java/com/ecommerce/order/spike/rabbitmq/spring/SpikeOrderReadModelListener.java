package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import com.ecommerce.order.order.model.event.OrderCreatedEvent;
import com.ecommerce.order.product.ProductRepository;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

//@Component
//@RabbitListener(queues = "order-summary-queue")
public class SpikeOrderReadModelListener {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();
    private ProductRepository repository;

    public SpikeOrderReadModelListener(ProductRepository repository) {
        this.repository = repository;
    }

    @RabbitHandler
    public void handleOrderCreated(OrderCreatedEvent orderCreatedEvent) {
        logger.info("handle orderCreatedEvent:{}", orderCreatedEvent.getPrice());
    }
//
//    @RabbitHandler
//    public void handleOrderUpdateEvent(OrderAddressChangedEvent orderAddressChangedEvent) {
//        repository.save(Product.create("1111", "dexc", BigDecimal.TEN));
////        if (new Random().nextBoolean()) {
//        logger.info("handle orderUpdatedEvent:{}", orderAddressChangedEvent.getNewAddress());
////        } else {
////            throw new RuntimeException("handle order created event failed.");
////        }
//    }

}
