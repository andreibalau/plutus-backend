package com.finance.plutus.model.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.product.Type;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class CreateProductDto {

	@NotBlank
	private String name;
	@NotBlank
	private String uom;
	@NotNull
	private Double price;
	private Type type;

}
