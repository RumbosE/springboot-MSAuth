package org.eduorg.msauth.user.domain.exceptions;

import org.eduorg.msauth.common.domain.exception.DomainException;

public class InvalidUserBirthdateException extends DomainException {
    public InvalidUserBirthdateException() {
        super("Invalid user birthday provided.");
    }
}
