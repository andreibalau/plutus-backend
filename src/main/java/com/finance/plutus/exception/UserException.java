package com.finance.plutus.exception;

public class UserException extends PlutusException {

	public UserException(String message) {
		super(message);
	}

	public static UserException userNotFound() {
		return new UserException("User not found!");
	}

	public static UserException userIsForbiddenOrUnauthorized() {
		return new UserException("User is not allowed to access this resource!");
	}

	public static UserException wrongCredentials() {
		return new UserException("Check the credentials!");
	}

	public static UserException emailAlreadyExists() {
		return new UserException("Email is already in use!");
	}
}
