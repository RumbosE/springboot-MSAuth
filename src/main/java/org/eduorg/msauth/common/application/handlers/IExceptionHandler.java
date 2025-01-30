package org.eduorg.msauth.common.application.handlers;

import org.springframework.web.server.ResponseStatusException;

public interface IExceptionHandler {
    ResponseStatusException handleException(Exception e);
}
