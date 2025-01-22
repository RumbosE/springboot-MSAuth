package org.eduorg.msauth.common.application.exceptions;

import org.eduorg.msauth.common.utils.base_exception.BaseException;
import org.eduorg.msauth.common.utils.base_exception.enumeration.ExceptionType;

public class ApplicationException extends BaseException {
    public ApplicationException(String message) {
        super(message, ExceptionType.APPLICATION_EXCEPTION);
    }
}
