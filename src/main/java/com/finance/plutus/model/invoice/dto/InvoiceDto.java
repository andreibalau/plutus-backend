package com.finance.plutus.model.invoice.dto;

import java.util.HashSet;
import java.util.Set;

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

	private Long id;
	private Double subtotal;
	private Double taxes;
	private Double total;
	private Long date;
	private PartnerDto vendor;
	private PartnerDto client;
	private Long serial;
	private String serialName;
	private Set<InvoiceLineDto> lines = new HashSet<>();
	private Type type;
	private Status status;

}
