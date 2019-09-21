package com.finance.plutus.model.partner.dto;

import com.finance.plutus.model.address.dto.AddressDto;
import com.finance.plutus.model.partner.Partner;
import com.finance.plutus.model.partner.Type;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.finance.plutus.model.partner.Type.BUSINESS;

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
	private Set<AddressDto> addresses = Collections.emptySet();

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
				.addresses(addresses
						.stream()
						.map(AddressDto::toAddress)
						.collect(Collectors.toSet()))
				.build();
	}
}
