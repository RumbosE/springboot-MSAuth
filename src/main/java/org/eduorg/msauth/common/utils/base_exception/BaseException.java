package org.eduorg.msauth.common.utils.base_exception;

import org.eduorg.msauth.common.utils.base_exception.enumeration.ExceptionType;

public class BaseException extends RuntimeException {
    private final ExceptionType type;

    public BaseException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public ExceptionType getType() {
        return type;
    }
}
