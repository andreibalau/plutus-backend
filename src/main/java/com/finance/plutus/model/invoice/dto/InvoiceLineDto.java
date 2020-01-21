package com.finance.plutus.model.invoice.dto;

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

	private Long id;
	private Long invoice;
	private ProductDto product;
	private String uom;
	private Double quantity;
	private Double price;
	private Double subtotal;
	private Double taxes;
	private Double total;

}
