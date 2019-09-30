package com.finance.plutus.model.exchange;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Plutus
 * Created by catalin on 28.09.2019
 */
@Getter
@AllArgsConstructor
public enum Currency {
	RON("RON", ""),
	AED("AED", ""),
	AUD("AUD", ""),
	BGN("BGN", ""),
	BRL("BRL", ""),
	CAD("CAD", ""),
	CHF("CHF", ""),
	CNY("CNY", ""),
	CZK("CZK", ""),
	DKK("DKK", ""),
	EGP("EGP", ""),
	EUR("EUR", ""),
	GBP("GBP", ""),
	HRK("HRK", ""),
	HUF("HUF", ""),
	INR("INR", ""),
	JPY("JPY", ""),
	KRW("KRW", ""),
	MDL("MDL", ""),
	MXN("MXN", ""),
	NOK("NOK", ""),
	NZD("NZD", ""),
	PLN("PLN", ""),
	RSD("RSD", ""),
	RUB("RUB", ""),
	SEK("SEK", ""),
	THB("THB", ""),
	TRY("TRY", ""),
	UAH("UAH", ""),
	USD("USD", ""),
	XAU("XAU", ""),
	XDR("XDR", ""),
	ZAR("ZAR", "");

	private String currency;
	private String name;
}
