package com.finance.plutus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

    private Integer code;
    private String message;

    public static ErrorDto from(ServiceException serviceException) {
        return new ErrorDto(serviceException.getCode(), serviceException.getMessage());
    }

}
