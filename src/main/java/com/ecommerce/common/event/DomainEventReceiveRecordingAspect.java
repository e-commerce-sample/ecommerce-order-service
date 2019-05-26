package com.ecommerce.common.event;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DomainEventReceiveRecordingAspect {
    private DomainEventReceiveRecorder domainEventReceiveRecorder;

    public DomainEventReceiveRecordingAspect(DomainEventReceiveRecorder domainEventReceiveRecorder) {
        this.domainEventReceiveRecorder = domainEventReceiveRecorder;
    }

    @Around("@annotation(org.springframework.amqp.rabbit.annotation.RabbitHandler) || @annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return domainEventReceiveRecorder.record(joinPoint);
    }
}