package com.finance.plutus.model.serial.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/30/2020
 */
@Getter
@Setter
public class CreateSerialDto {

	@NotBlank
	private String name;
	@NotNull
	private Long max;
	@NotNull
	private Long min;

}
