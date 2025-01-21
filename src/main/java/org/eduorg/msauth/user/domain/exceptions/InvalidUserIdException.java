package org.eduorg.msauth.user.domain.exceptions;

import org.eduorg.msauth.common.domain.exception.DomainException;

public class InvalidUserIdException extends DomainException {

        public InvalidUserIdException() {
            super("Invalid user id - don't follow domain rules");
        }
}
