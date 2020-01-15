package com.finance.plutus.model.invoice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.product.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class InvoiceLineDto {
	@NotNull
	private Long id;
	@NotNull
	private Long invoice;
	@NotNull
	private ProductDto product;
	@NotBlank
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
