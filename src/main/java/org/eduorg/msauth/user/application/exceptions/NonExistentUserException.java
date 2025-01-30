package org.eduorg.msauth.user.application.exceptions;

import org.eduorg.msauth.common.application.exceptions.ApplicationException;

public class NonExistentUserException extends ApplicationException {
    public NonExistentUserException() {
        super("User does not exist");
    }
}
