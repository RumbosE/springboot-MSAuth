package org.eduorg.msauth.common.infraestructure.logger;

import org.eduorg.msauth.common.application.aspects.logger.ILogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerAspectImpl implements ILogger {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerAspectImpl.class);

    @Override
    public void debug(String message) {
        LOG.debug(message);
    }

    @Override
    public void error(String message, Exception e) {
        LOG.error(message, e);
    }
}