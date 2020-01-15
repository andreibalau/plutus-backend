package com.finance.plutus.model.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.finance.plutus.model.user.User;
import lombok.Getter;
import lombok.Setter;

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
	@NotBlank
	private String address;

	public User toUser() {
		return User
				.builder()
				.firstName(firstName)
				.lastName(lastName)
				.phone(phone)
				.email(email.toLowerCase())
				.password(password)
				.address(address)
				.build();
	}

}
