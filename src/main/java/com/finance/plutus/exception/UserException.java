package com.finance.plutus.exception;

import org.springframework.http.HttpStatus;

public class UserException extends PlutusException {

	public UserException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

	public static UserException userNotFound() {
		return new UserException("User not found!", HttpStatus.BAD_REQUEST);
	}

	public static UserException userIsForbiddenOrUnauthorized() {
		return new UserException("User is not allowed to access this resource!", HttpStatus.FORBIDDEN);
	}

	public static UserException wrongCredentials() {
		return new UserException("Check the credentials!", HttpStatus.BAD_REQUEST);
	}

	public static UserException emailAlreadyExists() {
		return new UserException("Email is already in use!", HttpStatus.BAD_REQUEST);
	}
}
