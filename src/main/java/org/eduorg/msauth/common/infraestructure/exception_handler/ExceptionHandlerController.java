package org.eduorg.msauth.common.infraestructure.exception_handler;

import org.eduorg.msauth.common.application.exceptions.ApplicationException;
import org.eduorg.msauth.common.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController{


    @ExceptionHandler( value = { DomainException.class } )
    public ResponseEntity<BasicExceptionResponse> handleDomainException(DomainException e) {
        return new ResponseEntity<>(new BasicExceptionResponse(e.getClass().getSimpleName() , e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( value = { ApplicationException.class } )
    public ResponseEntity<BasicExceptionResponse> handleApplicationLayerException(ApplicationException e) {
        return new ResponseEntity<>(new BasicExceptionResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler( value = { MethodArgumentNotValidException.class } )
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException e) {
        List<ValidationError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { LoginException.class })
    public ResponseEntity<BasicExceptionResponse> handleLoginException() {
        return new ResponseEntity<>( new BasicExceptionResponse("Invalid Credentials", "email or password are incorrect please try again") ,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    public ResponseEntity<String> handleException(Exception e) {
        String message = e.getMessage() != null ? "Internal server error" + e.getMessage() : "Internal server error";
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
