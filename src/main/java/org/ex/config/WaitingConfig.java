package org.ex.config;

import java.time.Duration;

public enum WaitingConfig {

    IMPLICIT_WAIT(4),
    PAGE_LOAD_TIMEOUT(10),
    SCRIPT_TIMEOUT(6),
    WAITING_TIMEOUT(20);

    private final int seconds;

    WaitingConfig(int seconds) {
        this.seconds = seconds;
    }

    public Duration getDuration() {
        return Duration.ofSeconds(seconds);
    }
}
