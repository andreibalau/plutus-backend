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
	RON("RON", "Leu romanesc"),
	EUR("EUR", "Euro"),
	GBP("GBP", "Lira sterlina"),
	MDL("MDL", "Leu moldovenesc"),
	USD("USD", "Dolar american"),
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
	HRK("HRK", ""),
	HUF("HUF", ""),
	INR("INR", ""),
	JPY("JPY", ""),
	KRW("KRW", ""),
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
	XAU("XAU", ""),
	XDR("XDR", ""),
	ZAR("ZAR", "");

	private String currency;
	private String name;
}
