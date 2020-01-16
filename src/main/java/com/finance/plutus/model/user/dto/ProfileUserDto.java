package com.finance.plutus.model.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/16/2020
 */
@Getter
@Setter
public class ProfileUserDto {

	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String password;
	private String address;

}
