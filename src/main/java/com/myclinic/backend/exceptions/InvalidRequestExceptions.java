package com.myclinic.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestExceptions extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidRequestExceptions(String message){
        super(message);
    }
}
