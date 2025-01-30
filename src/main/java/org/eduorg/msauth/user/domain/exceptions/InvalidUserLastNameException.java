package org.eduorg.msauth.user.domain.exceptions;

import org.eduorg.msauth.common.domain.exception.DomainException;

public class InvalidUserLastNameException extends DomainException {
    public InvalidUserLastNameException() {
        super("User last name must be at least 5 characters long");
    }
}
