package com.gabrielklein.email.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JsonProcessingException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public JsonProcessingException(String ex) {
        super(ex);
    }
}