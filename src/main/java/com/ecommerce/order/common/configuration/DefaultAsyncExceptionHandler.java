package com.ecommerce.order.common.configuration;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class DefaultAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        logger.error("Error while executing async task-{}:", method.getName(), ex);
    }
}
