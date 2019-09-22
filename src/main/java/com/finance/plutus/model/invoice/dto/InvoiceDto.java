package com.finance.plutus.model.invoice.dto;

import com.finance.plutus.model.invoice.Invoice;
import com.finance.plutus.model.partner.dto.PartnerDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Builder
@Data
public class InvoiceDto {
	@NotNull
	private Long id;
	@NotNull
	private Double subtotal;
	@NotNull
	private Double taxes;
	@NotNull
	private Double total;
	@NotNull
	private Long date;
	@NotNull
	private PartnerDto vendor;
	@NotNull
	private PartnerDto client;
	@NotNull
	private Long serial;
	@NotBlank
	private String serialName;
	@NotNull
	private List<LineDto> lines;

	public static InvoiceDto from(Invoice invoice) {
		return InvoiceDto
				.builder()
				.id(invoice.getId())
				.subtotal(invoice.getSubtotal())
				.taxes(invoice.getTaxes())
				.total(invoice.getTotal())
				.date(invoice.getDate())
				.vendor(PartnerDto.from(invoice.getVendor()))
				.client(PartnerDto.from(invoice.getClient()))
				.serial(invoice.getSerial().getId())
				.serialName(invoice.getSerialName())
				.lines(invoice
						.getLines()
						.stream()
						.map(LineDto::from)
						.collect(Collectors.toList()))
				.build();
	}
}
