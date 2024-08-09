package com.gabrielklein.email.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidationDataIntegrityException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ValidationDataIntegrityException(String ex) {
        super(ex);
    }
}