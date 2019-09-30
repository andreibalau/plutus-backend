package com.finance.plutus.model.user.dto;

import com.finance.plutus.model.user.User;
import lombok.Builder;
import lombok.Data;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Builder
@Data
public class LoggedUserDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;

	public static LoggedUserDto from(User user) {
		return LoggedUserDto
				.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.build();
	}
}
