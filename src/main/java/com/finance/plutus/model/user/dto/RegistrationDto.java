package com.finance.plutus.model.user.dto;

import com.finance.plutus.model.address.dto.AddressDto;
import com.finance.plutus.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@NoArgsConstructor
public class RegistrationDto {
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String phone;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	private Set<AddressDto> addresses = Collections.emptySet();

	public User toUser() {
		return User
				.builder()
				.firstName(firstName)
				.lastName(lastName)
				.phone(phone)
				.email(email.toLowerCase())
				.password(password)
				.addresses(addresses
						.stream()
						.map(AddressDto::toAddress)
						.collect(Collectors.toSet()))
				.build();
	}
}
