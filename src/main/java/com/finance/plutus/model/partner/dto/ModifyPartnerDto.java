package com.finance.plutus.model.partner.dto;

import javax.validation.constraints.NotBlank;

import com.finance.plutus.model.partner.Type;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class ModifyPartnerDto {
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String phone;
	@NotBlank
	private String email;
	@NotBlank
	private String bank;
	@NotBlank
	private String bankAccount;
	@NotBlank
	private String address;
	@NotBlank
	private String cif;
	@NotBlank
	private String regCom;
	private Type type;
}
