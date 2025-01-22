package org.eduorg.msauth.user.application.exceptions;

import org.eduorg.msauth.common.application.exceptions.ApplicationException;

public class UserEmailAlreadyExistsException extends ApplicationException {
    public UserEmailAlreadyExistsException(String email) {
        super("User email " + email + " already exists in the system");
    }
}
