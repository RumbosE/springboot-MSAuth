package org.eduorg.msauth.user.domain.exceptions;

import org.eduorg.msauth.common.domain.exception.DomainException;

public class InvalidUserPhoneException extends DomainException {
    public InvalidUserPhoneException(String message) {
        super('"' + message + '"' + " is not a valid phone - code must be a valid phone code - number must be 11 digits long");
    }
}