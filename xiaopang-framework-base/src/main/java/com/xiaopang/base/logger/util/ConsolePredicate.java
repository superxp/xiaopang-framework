package com.xiaopang.base.logger.util;

import com.xiaopang.base.logger.env.LoggerLevel;
import org.apache.logging.log4j.Level;
import org.springframework.core.env.Environment;

/**
 * @author necho.duan
 * @title: ConsolePredicate
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 20:52
 */
public abstract class ConsolePredicate {
    public ConsolePredicate() {
    }

    public static Level getAmityLevel(Environment environment, LoggerLevel envLogLevel) {
        String consoleLevel = environment.getProperty("LOG_CONSOLE_LEVEL", envLogLevel.getConsole());
        Level toLevel = Level.toLevel(consoleLevel);
        return toLevel.intLevel() <= Level.ERROR.intLevel() ? Level.WARN : toLevel;
    }

    public static Level getFinalLevel(Environment environment, LoggerLevel envLogLevel) {
        String consoleLevel = environment.getProperty("LOG_CONSOLE_LEVEL", envLogLevel.getConsole());
        Level amityLevel = getAmityLevel(environment, envLogLevel);
        Level toLevel = Level.toLevel(consoleLevel);
        return toLevel.compareTo(amityLevel) != 0 ? toLevel : null;
    }
}
