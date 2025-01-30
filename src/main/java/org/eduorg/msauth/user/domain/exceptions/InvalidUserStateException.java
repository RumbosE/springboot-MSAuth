package org.eduorg.msauth.user.domain.exceptions;

import org.eduorg.msauth.common.domain.exception.DomainException;

public class InvalidUserStateException extends DomainException {
    public InvalidUserStateException() {
        super("Invalid User State");
    }
}
