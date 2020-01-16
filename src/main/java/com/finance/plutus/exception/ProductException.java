package com.finance.plutus.exception;

import org.springframework.http.HttpStatus;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
public class ProductException extends PlutusException {

	public ProductException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

	public static ProductException productNotFound() {
		return new ProductException("Product not found!", HttpStatus.NOT_FOUND);
	}

}
