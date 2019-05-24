package com.ecommerce.order.spike.rabbitmq.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DomainEventReceivingAspect {
    private DomainEventReceiver domainEventReceiver;

    public DomainEventReceivingAspect(DomainEventReceiver domainEventReceiver) {
        this.domainEventReceiver = domainEventReceiver;
    }

    @Around("@annotation(org.springframework.amqp.rabbit.annotation.RabbitHandler) || @annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return domainEventReceiver.aa(joinPoint);
    }
}