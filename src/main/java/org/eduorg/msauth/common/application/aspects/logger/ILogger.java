package org.eduorg.msauth.common.application.aspects.logger;

public interface ILogger {
    void debug(String message);
    void error(String message, Exception e);
}
