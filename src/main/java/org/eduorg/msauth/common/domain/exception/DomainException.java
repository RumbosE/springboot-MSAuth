package org.eduorg.msauth.common.domain.exception;

import org.eduorg.msauth.common.utils.base_exception.BaseException;
import org.eduorg.msauth.common.utils.base_exception.enumeration.ExceptionType;

public class DomainException extends BaseException {

    public DomainException( String message ) {
        super( message, ExceptionType.DOMAIN_EXCEPTION );
    }

}
