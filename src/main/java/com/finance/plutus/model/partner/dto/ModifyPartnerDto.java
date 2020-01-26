package com.finance.plutus.model.partner.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private String name;
	private String phone;
	private String email;
	private String iban;
	private String address;
	@NotBlank
	private String city;
	private String state;
	@NotBlank
	private String country;
	private String cif;
	private String regCom;
	@NotNull
	private Type type;
}
