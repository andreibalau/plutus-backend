package com.finance.plutus.model.invoice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import com.finance.plutus.model.invoice.Status;
import com.finance.plutus.model.invoice.Type;
import com.finance.plutus.model.partner.dto.PartnerDto;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
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
	private List<InvoiceLineDto> lines;
	@NotNull
	private Type type;
	@NotNull
	private Status status;
}
