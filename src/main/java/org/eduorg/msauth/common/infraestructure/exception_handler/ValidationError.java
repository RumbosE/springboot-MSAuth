package org.eduorg.msauth.common.infraestructure.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {
    private String field;
    private String message;
}