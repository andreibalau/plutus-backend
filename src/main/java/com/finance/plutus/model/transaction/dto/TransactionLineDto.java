package com.finance.plutus.model.transaction.dto;

import com.finance.plutus.model.product.dto.ProductDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Data
@Builder
public class TransactionLineDto {
	@NotNull
	private Long id;
	@NotNull
	private Long transaction;
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
