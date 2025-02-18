package org.ex.config;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;
import java.time.Duration;

public class TimingExtension implements BeforeEachCallback, AfterEachCallback {

    private static final Logger logger = LoggerFactory.getLogger(TimingExtension.class);

    private static final String START_TIME = "start time";

    @Override
    public void beforeEach(ExtensionContext context) {
        getStore(context).put(START_TIME, Instant.now());
        logger.info("Начало теста: {}", context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        Instant startTime = getStore(context).remove(START_TIME, Instant.class);
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        long millis = duration.toMillis();
        logger.info("___{}. Время выполнения: {} мс", context.getDisplayName(), millis);
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }
}
