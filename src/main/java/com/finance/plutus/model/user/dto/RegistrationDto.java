package com.finance.plutus.model.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
public class RegistrationDto {

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String phone;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@Valid
	@NotNull
	private BusinessDto business;

}
