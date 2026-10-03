package com.myclinic.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(InvalidRequestExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidRequestException(InvalidRequestExceptions exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setDescription(request.getDescription(false));
        errorMessage.setTimestamp(Instant.now());
        return errorMessage;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception ex , WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setDescription(webRequest.getDescription(false));
        errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMessage.setTimestamp(Instant.now());
        return errorMessage;
    }


}
