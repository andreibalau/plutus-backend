package com.finance.plutus.model.partner.dto;

import static com.finance.plutus.model.partner.Type.BUSINESS;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.partner.Type;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 22.09.2019
 */
@Getter
@Setter
public class CreatePartnerDto {
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
	private String cif;
	@NotBlank
	private String regCom;
	private Type type;
	private List<Long> addresses = Collections.emptyList();

	public Partner toPartner() {
		return Partner
				.builder()
				.firstName(firstName)
				.lastName(lastName)
				.phone(phone)
				.email(email)
				.bank(bank)
				.bankAccount(bankAccount)
				.cif(cif)
				.regCom(regCom)
				.type(type != null ? type : BUSINESS)
				.build();
	}
}
