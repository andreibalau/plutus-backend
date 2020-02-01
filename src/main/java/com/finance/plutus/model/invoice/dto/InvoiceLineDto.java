package com.finance.plutus.model.invoice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.product.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/20/2020
 */
@Getter
@Setter
public class InvoiceLineDto {

	private Long id;
	@Valid
	@NotNull
	private ProductDto product;
	private String uom;
	@NotNull
	private Double quantity;
	@NotNull
	private Double price;
	@NotNull
	private Double subtotal;
	@NotNull
	private Double taxes;
	@NotNull
	private Double total;

}
