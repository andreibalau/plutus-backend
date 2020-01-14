package com.finance.plutus.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ErrorDto> handleException(Throwable exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(ErrorDto.from(exception.getMessage()), INTERNAL_SERVER_ERROR);
    }

}
