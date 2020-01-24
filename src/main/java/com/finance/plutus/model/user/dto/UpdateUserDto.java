package com.finance.plutus.model.user.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
@Getter
@Setter
public class UpdateUserDto {

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String phone;
	@Valid
	@NotNull
	private BusinessDto business;

}
