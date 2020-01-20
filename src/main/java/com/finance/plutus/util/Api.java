package com.finance.plutus.util;

/**
 * Plutus
 * Created by catalin on 14.01.2020
 */
public class Api {

	private Api() {
		throw new IllegalStateException("You cannot instantiate this class");
	}

	private static final String API = "/api/v1";
	public static final String PRODUCTS = API + "/products";
	public static final String PARTNERS = API + "/partners";
	public static final String INVOICES = API + "/invoices";
	public static final String EXCHANGES = API + "/exchanges";
	public static final String ANAF = API + "/anaf";
	public static final String ADDRESSES = API + "/addresses";
	public static final String CITIES = API + "/cities";
	public static final String COUNTRIES = API + "/countries";
	public static final String COUNTIES = API + "/counties";
	public static final String USERS = API + "/users";
	public static final String LOGIN = "/login";
	public static final String REGISTER = "/register";
	public static final String CHECK_EMAIL = "/email";

}
