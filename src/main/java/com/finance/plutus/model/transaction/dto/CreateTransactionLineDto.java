package com.finance.plutus.model.transaction.dto;

import com.finance.plutus.model.transaction.TransactionLine;
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
public class CreateTransactionLineDto {
	@NotNull
	private Long product;
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

	public TransactionLine toTransactionLine() {
		return TransactionLine
				.builder()
				.uom(uom)
				.quantity(quantity)
				.price(price)
				.subtotal(subtotal)
				.taxes(taxes)
				.total(total)
				.build();
	}
}
