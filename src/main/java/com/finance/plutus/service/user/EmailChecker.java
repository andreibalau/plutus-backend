package com.finance.plutus.service.user;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
public interface EmailChecker {
	boolean exists(String email);
}
