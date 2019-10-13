package com.finance.plutus.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> handleException(Throwable exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(ErrorDto.from(PlutusException.factory(exception.getMessage())), INTERNAL_SERVER_ERROR);
    }
}
