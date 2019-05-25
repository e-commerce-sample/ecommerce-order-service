package com.ecommerce.order.spike.rabbitmq.spring;

import com.ecommerce.order.common.event.DomainEvent;
import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DomainEventReceiveRecorder {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private NamedParameterJdbcTemplate jdbcTemplate;

    public DomainEventReceiveRecorder(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if (args[0] instanceof DomainEvent) {
            logger.info("======" + args[0].getClass().getName());
        }
        // do business success
        // do event table success
        // throw exception
//        throw new RuntimeException();
        Object proceed = joinPoint.proceed();

        return proceed;
    }
}
