package com.finance.plutus.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {
	private String username;
	private String password;
}
