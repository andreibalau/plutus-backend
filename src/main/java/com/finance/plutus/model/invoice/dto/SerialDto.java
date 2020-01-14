package com.finance.plutus.model.invoice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Builder
@Data
public class SerialDto {
	@NotNull
	private Long id;
	@NotBlank
	private String name;
	@NotNull
	private Long number;
	@NotNull
	private Long max;
	@NotNull
	private Long min;
}