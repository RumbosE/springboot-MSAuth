package org.eduorg.msauth.common.infraestructure.exception_handler;

import org.eduorg.msauth.common.application.exceptions.ApplicationException;
import org.eduorg.msauth.common.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController{


    @ExceptionHandler( value = { DomainException.class } )
    public ResponseEntity<ValidationError> handleDomainException(DomainException e) {
        return new ResponseEntity<>(new ValidationError("domain", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( value = { ApplicationException.class } )
    public ResponseEntity<ValidationError> handleApplicationLayerException(ApplicationException e) {
        return new ResponseEntity<>(new ValidationError("application", e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler( value = { MethodArgumentNotValidException.class } )
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException e) {
        List<ValidationError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Something strange happened!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
