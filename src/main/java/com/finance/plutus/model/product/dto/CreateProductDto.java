package com.finance.plutus.model.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.product.Product;
import lombok.Data;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Data
public class CreateProductDto {
	@NotBlank
	private String name;
	@NotBlank
	private String uom;
	@NotNull
	private Double price;

	public Product toProduct() {
		return Product
				.builder()
				.name(name)
				.uom(uom)
				.price(price)
				.build();
	}
}
