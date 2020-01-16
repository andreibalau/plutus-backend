package com.finance.plutus.exception;

import org.springframework.http.HttpStatus;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
public class PartnerException extends PlutusException {

	public PartnerException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

	public static PartnerException partnerNotFound() {
		return new PartnerException("Partner not found!", HttpStatus.NOT_FOUND);
	}

}
