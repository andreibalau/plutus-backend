package com.finance.plutus.exception;

import org.springframework.http.HttpStatus;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
public class AnafException extends PlutusException {

	public AnafException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

}
