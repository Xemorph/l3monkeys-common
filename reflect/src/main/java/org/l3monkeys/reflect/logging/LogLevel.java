package org.l3monkeys.reflect.logging;

/**
 * Encodes legal logging level constants for the {@link Logger} class.
 */
public enum LogLevel {
    /*----------------------------------------------------------------------*\
                        Enumeration Constant Values
    \*----------------------------------------------------------------------*/

    /**
     * Log message at "debug" level
     */
    DEBUG,
    /**
     * Log message at "error" level
     */
    ERROR,
    /**
     * Log message at "fatal error" level
     */
    FATAL,
    /**
     * Log message at "informational message" level
     */
    INFO,
    /**
     * Log message at "trace" level
     */
    TRACE,
    /**
     * Log message at "warning" level
     */
    WARNING;
}