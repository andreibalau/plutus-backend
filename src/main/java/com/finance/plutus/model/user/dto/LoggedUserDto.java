package com.finance.plutus.model.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
public class LoggedUserDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String token;

}
