package com.finance.plutus.model.product.dto;

import com.finance.plutus.model.product.Type;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class ProductDto {

	private Long id;
	private String name;
	private String uom;
	private Double unitPrice;
	private Double tva;
	private Double totalPrice;
	private Type type;

}
