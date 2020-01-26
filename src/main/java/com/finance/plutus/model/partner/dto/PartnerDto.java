package com.finance.plutus.model.partner.dto;

import com.finance.plutus.model.partner.Type;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 1/26/2020
 */
@Getter
@Setter
public class PartnerDto {

	private Long id;
	private String name;
	private String phone;
	private String email;
	private String iban;
	private String address;
	private String city;
	private String state;
	private String country;
	private String cif;
	private String regCom;
	private Type type;

}
