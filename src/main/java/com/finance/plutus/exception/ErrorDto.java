package com.finance.plutus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

    private String message;

    public static ErrorDto from(PlutusException plutusException) {
        return new ErrorDto(plutusException.getMessage());
    }

}
