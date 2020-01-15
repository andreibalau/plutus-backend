package com.finance.plutus.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PlutusException extends RuntimeException {

    private final HttpStatus httpStatus;

    public PlutusException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
