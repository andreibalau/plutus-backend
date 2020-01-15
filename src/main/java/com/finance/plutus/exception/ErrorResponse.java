package com.finance.plutus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    public static ErrorResponse from(PlutusException plutusException) {
        return new ErrorResponse(plutusException.getMessage());
    }

    public static ErrorResponse from(String message) {
        return new ErrorResponse(message);
    }

}
