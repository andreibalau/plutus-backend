package com.finance.plutus.model.user.dto;

import com.finance.plutus.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

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
	@NotNull
	private SettingsDto settings;
	private List<Long> addresses = Collections.emptyList();

	public User toUser() {
		return User
				.builder()
				.firstName(firstName)
				.lastName(lastName)
				.phone(phone)
				.email(email.toLowerCase())
				.password(password)
				.build();
	}
}
