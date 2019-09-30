package com.finance.plutus.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class ServiceException extends RuntimeException {

    private final int code;

    public static final int GENERAL_CODE = 0;
    public static final int EMAIL_ALREADY_EXISTS_CODE = 1;
    public static final int NOT_FOUND_ERROR_CODE = 2;
    public static final int INVALID_JSON_CODE = 3;
    public static final int CONSTRAINTS_VIOLATION_CODE = 4;
    public static final int NO_REQUEST_BODY_CODE = 5;
    public static final int UNAUTHORIZED_CODE = 6;
    public static final int FORBIDDEN_CODE = 7;

    private ServiceException(CodeMessage codeMessage) {
        super(codeMessage.message);
        this.code = codeMessage.code;
    }

    private ServiceException(CodeMessage codeMessage, String message) {
        super(codeMessage.message + ": " + message);
        this.code = codeMessage.code;
    }

    public static ServiceException emailAlreadyExists() {
        return new ServiceException(CodeMessage.EMAIL_ALREADY_EXISTS);
    }

    public static ServiceException notFound() {
        return new ServiceException(CodeMessage.NOT_FOUND);
    }

    public static ServiceException generalException(String message) {
        return new ServiceException(CodeMessage.GENERAL, message);
    }

    public static ServiceException invalidJson(List<String> messages) {
        String message = String.join("; ", messages);
        return new ServiceException(CodeMessage.INVALID_JSON, message);
    }

    public static ServiceException constraintsViolation(List<String> messages) {
        String message = String.join("; ", messages);
        return new ServiceException(CodeMessage.CONSTRAINTS_VIOLATION, message);
    }

    public static ServiceException constraintsViolation(String message) {
        return constraintsViolation(Collections.singletonList(message));
    }

    public static ServiceException unauthorized() {
        return new ServiceException(CodeMessage.UNAUTHORIZED);
    }

    public static ServiceException forbidden() {
        return new ServiceException(CodeMessage.FORBIDDEN);
    }


    public static ServiceException noBody() {
        return new ServiceException(CodeMessage.NO_REQUEST_BODY);
    }

    private enum CodeMessage {

        GENERAL(GENERAL_CODE, "Something went wrong"),
        EMAIL_ALREADY_EXISTS(EMAIL_ALREADY_EXISTS_CODE, "Email already exists"),
        NOT_FOUND(NOT_FOUND_ERROR_CODE, "Resource not found"),
        INVALID_JSON(INVALID_JSON_CODE, "Invalid json object"),
        CONSTRAINTS_VIOLATION(CONSTRAINTS_VIOLATION_CODE, "Invalid entity fields"),
        NO_REQUEST_BODY(NO_REQUEST_BODY_CODE, "Request body is empty"),
        UNAUTHORIZED(UNAUTHORIZED_CODE, "Username or password not correct"),
        FORBIDDEN(FORBIDDEN_CODE, "Forbidden");

        private final String message;
        private final int code;

        CodeMessage(int code, String message) {
            this.message = message;
            this.code = code;
        }

    }

}
