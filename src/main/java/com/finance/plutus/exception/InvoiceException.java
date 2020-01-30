package com.finance.plutus.exception;

import org.springframework.http.HttpStatus;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
public class InvoiceException extends PlutusException {

	public InvoiceException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

	public static InvoiceException invoiceNotFound() {
		return new InvoiceException("Invoice not found!", HttpStatus.NOT_FOUND);
	}

	public static InvoiceException invoiceCannotBeUpdated() {
		return new InvoiceException("Invoice cannot be updated!", HttpStatus.BAD_REQUEST);
	}

}
