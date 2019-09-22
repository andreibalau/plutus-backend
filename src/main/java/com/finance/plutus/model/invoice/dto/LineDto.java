package com.finance.plutus.model.invoice.dto;

import com.finance.plutus.model.invoice.Line;
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
public class LineDto {
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

	public static LineDto from(Line line) {
		return LineDto
				.builder()
				.id(line.getId())
				.invoice(line.getInvoice().getId())
				.product(ProductDto.from(line.getProduct()))
				.uom(line.getUom())
				.quantity(line.getQuantity())
				.price(line.getPrice())
				.subtotal(line.getSubtotal())
				.taxes(line.getTaxes())
				.total(line.getTotal())
				.build();
	}
}
