package com.finance.plutus.model.serial.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
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
