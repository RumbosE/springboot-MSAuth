package org.eduorg.msauth.user.domain.exceptions;

import org.eduorg.msauth.common.domain.exception.DomainException;

public class InvalidUserEmailException extends DomainException {
    public InvalidUserEmailException() {
        super("Invalid user email - must be a valid email address");
    }
}
