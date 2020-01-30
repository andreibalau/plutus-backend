package com.finance.plutus.util;

/**
 * Plutus
 * Created by catalin on 14.01.2020
 */
public class Api {

	private Api() {
		throw new IllegalStateException("You cannot instantiate this class");
	}

	private static final String API_V_1 = "/api/v1";
	public static final String PRODUCTS = API_V_1 + "/products";
	public static final String PARTNERS = API_V_1 + "/partners";
	public static final String INVOICES = API_V_1 + "/invoices";
	public static final String EXCHANGES = API_V_1 + "/exchanges";
	public static final String ANAF = API_V_1 + "/anaf";
	public static final String USERS = API_V_1 + "/users";
	public static final String SERIALS = API_V_1 + "/serials";
	public static final String LOGIN = "/login";
	public static final String REGISTER = "/register";
	public static final String CHECK_EMAIL = "/email";

}
