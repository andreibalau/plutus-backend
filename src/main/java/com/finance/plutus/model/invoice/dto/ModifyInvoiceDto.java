package com.finance.plutus.model.invoice.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

import com.finance.plutus.model.exchange.Currency;
import com.finance.plutus.model.invoice.Type;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Getter
@Setter
public class ModifyInvoiceDto {

	@NotNull
	private Double subtotal;
	@NotNull
	private Double taxes;
	@NotNull
	private Double total;
	@NotNull
	private Long date;
	@NotNull
	private Long partnerId;
	@NotNull
	private List<ModifyInvoiceLineDto> lines;
	@NotNull
	private Type type;
	private Long serialId;
	private Currency currency;

}
