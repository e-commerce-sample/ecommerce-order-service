package com.ecommerce.order.common.utils;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskRunner {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();
    private static final int THIRTY_MIN = 30 * 60 * 1000;
    private static final int ONE_MIN = 60 * 1000;

    @Scheduled(fixedDelay = 600000)
    @SchedulerLock(name = "scheduledTask1", lockAtMostFor = THIRTY_MIN, lockAtLeastFor = 1000)
    public void run1() throws InterruptedException {
        logger.info("Run scheduled task1.");
    }

    @Scheduled(cron = "0 0/20 * * * ?")
    @SchedulerLock(name = "scheduledTask2", lockAtMostFor = THIRTY_MIN, lockAtLeastFor = ONE_MIN)
    public void run2() {
        logger.info("Run scheduled task2.");
    }
}
