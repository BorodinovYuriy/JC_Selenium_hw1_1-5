package org.ex.config;

import java.time.Duration;

public enum WaitingConfig {

    IMPLICIT_WAIT(2),
    PAGE_LOAD_TIMEOUT(8),
    SCRIPT_TIMEOUT(4),
    WAITING_TIMEOUT(20);

    private final int seconds;

    WaitingConfig(int seconds) {
        this.seconds = seconds;
    }

    public Duration getDuration() {
        return Duration.ofSeconds(seconds);
    }
}
