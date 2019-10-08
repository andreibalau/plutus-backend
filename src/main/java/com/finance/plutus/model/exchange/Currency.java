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
	EUR("EUR", "Euro"),
	GBP("GBP", "Lira sterlina"),
	HRK("HRK", ""),
	HUF("HUF", "Forint maghiar"),
	INR("INR", ""),
	JPY("JPY", ""),
	KRW("KRW", ""),
	MDL("MDL", "Leu moldovenesc"),
	MXN("MXN", ""),
	NOK("NOK", ""),
	NZD("NZD", ""),
	PLN("PLN", ""),
	RSD("RSD", ""),
	RUB("RUB", "Rubla ruseasca"),
	SEK("SEK", ""),
	THB("THB", ""),
	TRY("TRY", ""),
	UAH("UAH", ""),
	USD("USD", "Dolar american"),
	XAU("XAU", ""),
	XDR("XDR", ""),
	ZAR("ZAR", "");

	private String currency;
	private String name;
}
