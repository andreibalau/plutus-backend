package com.finance.plutus.exception;

import org.springframework.http.HttpStatus;

/**
 * Plutus
 * Created by catalin on 1/30/2020
 */
public class SerialException extends PlutusException {

	public SerialException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

	public static SerialException serialNotFound() {
		return new SerialException("Serial cannot be found!", HttpStatus.NOT_FOUND);
	}

	public static SerialException serialNumberOverflow() {
		return new SerialException("Serial number has reached the maximum limit!", HttpStatus.SERVICE_UNAVAILABLE);
	}

	public static SerialException serialAlreadyExists() {
		return new SerialException("Serial already exists!", HttpStatus.BAD_REQUEST);
	}

}
