package com.celestium.manager;

import com.google.common.flogger.FluentLogger;
import lombok.Getter;

public class LoggingManager {

    @Getter
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

}
