package com.finance.plutus.model.transaction.dto;

import com.finance.plutus.model.transaction.invoice.InvoiceLine;
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
public class InvoiceLineDto {
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

	public static InvoiceLineDto from(InvoiceLine invoiceLine) {
		return InvoiceLineDto
				.builder()
				.id(invoiceLine.getId())
				.invoice(invoiceLine.getInvoice().getId())
				.product(ProductDto.from(invoiceLine.getProduct()))
				.uom(invoiceLine.getUom())
				.quantity(invoiceLine.getQuantity())
				.price(invoiceLine.getPrice())
				.subtotal(invoiceLine.getSubtotal())
				.taxes(invoiceLine.getTaxes())
				.total(invoiceLine.getTotal())
				.build();
	}
}
