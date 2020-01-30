package com.finance.plutus.model.invoice.dto;

import com.finance.plutus.model.exchange.Currency;
import com.finance.plutus.model.invoice.Status;
import com.finance.plutus.model.invoice.Type;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Getter
@Setter
public class PreviewInvoiceDto {

	private Long id;
	private Double subtotal;
	private Double total;
	private Double taxes;
	private Long date;
	private Type type;
	private Status status;
	private Currency currency;

}
