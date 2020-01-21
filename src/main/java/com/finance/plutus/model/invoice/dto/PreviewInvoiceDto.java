package com.finance.plutus.model.invoice.dto;

import javax.validation.constraints.NotNull;

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

	@NotNull
	private Long id;
	@NotNull
	private Double subtotal;
	@NotNull
	private Double total;
	@NotNull
	private Double taxes;
	@NotNull
	private Long date;
	@NotNull
	private Type type;
	@NotNull
	private Status status;

}
