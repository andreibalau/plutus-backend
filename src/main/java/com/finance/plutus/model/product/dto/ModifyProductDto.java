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
public class ModifyProductDto {

	@NotBlank
	private String name;
	@NotNull
	private Double unitPrice;
	@NotNull
	private Double totalPrice;
	@NotNull
	private Type type;
	@NotNull
	private Double tva;
	private String uom;

}
