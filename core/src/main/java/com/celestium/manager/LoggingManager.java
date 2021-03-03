package com.celestium.manager;

import com.google.common.flogger.FluentLogger;
import lombok.Getter;

/**
 * Singleton instance for the logging class.
 */
public class LoggingManager {

    @Getter
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

}
