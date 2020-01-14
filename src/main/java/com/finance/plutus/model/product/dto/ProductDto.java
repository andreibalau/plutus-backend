package com.finance.plutus.model.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.product.Product;
import lombok.Builder;
import lombok.Data;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Builder
@Data
public class ProductDto {
	@NotNull
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String uom;
	@NotNull
	private Double price;

	public static ProductDto from(Product product) {
		return ProductDto
				.builder()
				.id(product.getId())
				.name(product.getName())
				.uom(product.getUom())
				.price(product.getPrice())
				.build();
	}
}
