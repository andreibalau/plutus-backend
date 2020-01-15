package com.finance.plutus.model.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class ProductDto {

	@NotNull
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String uom;
	@NotNull
	private Double price;

}
