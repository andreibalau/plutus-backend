package com.finance.plutus.exception;

import lombok.Getter;

@Getter
public class PlutusException extends RuntimeException {

    private final String message;

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists.";
    public static final String FORBIDDEN_OR_UNAUTHORIZED = "You are not allowed to access this resource.";
    public static final String WRONG_CREDENTIALS = "Wrong credentials.";
    public static final String GENERAL = "Something went wrong.";
    public static final String NOT_FOUND = "Resource not found";

    private PlutusException(String message) {
        this.message = message;
    }

    public static PlutusException factory(String message) {
        return new PlutusException(message);
    }

    public static PlutusException notFound() {
        return new PlutusException(NOT_FOUND);
    }
    public static PlutusException emailAlreadyExists() {
        return new PlutusException(EMAIL_ALREADY_EXISTS);
    }
    public static PlutusException general() {
        return new PlutusException(GENERAL);
    }
    public static PlutusException forbiddenOrUnauthorized() {
        return new PlutusException(FORBIDDEN_OR_UNAUTHORIZED);
    }
    public static PlutusException wrongCredentials() {
        return new PlutusException(WRONG_CREDENTIALS);
    }
}
