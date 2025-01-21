package org.eduorg.msauth.user.domain.exceptions;

import org.eduorg.msauth.common.domain.exception.DomainException;

public class InvalidUserNameLengthException extends DomainException {
    public InvalidUserNameLengthException() {
        super("Invalid user name length - must be at least 4 characters long");
    }
}
